package com.example.mydiary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class WriteActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 2;
    static final int REQUEST_PHOTO_SELESTION = 3;

    Button btnSave;
    Button btnImage;
    DBHelper dbHelper;
    EditText editTitle;
    EditText editContent;
    ImageView imageDiary;
    String currentPhotoPath;
    Uri photoURI;
    File photoFile;
    Button btnPhoto;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        imageDiary = findViewById(R.id.imageDiary);

        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        btnSave = findViewById(R.id.btnSave);
        btnImage = findViewById(R.id.btnImage);
        btnPhoto = findViewById(R.id.btnPhoto);

        dbHelper = new DBHelper(getApplicationContext());

        Intent intent = getIntent();//값을 가지고 온다
        //값이 비어있으면 id값을 str에 넣어준다?

        boolean str = TextUtils.isEmpty(intent.getStringExtra("id"));

        editTitle.setText(intent.getStringExtra("title"));
        editContent.setText(intent.getStringExtra("content"));
        if(intent.getStringExtra("img") != null){
            imageDiary.setImageURI(Uri.parse(intent.getStringExtra("img")));
        }


        btnSave.setOnClickListener(v->{
            DiaryVO vo = new DiaryVO();

        vo.setTitle(editTitle.getText().toString());
        vo.setContent(editContent.getText().toString());
        vo.setImg(currentPhotoPath);

        System.out.println(editTitle.getText().toString());
        System.out.println(editContent.getText().toString());
        System.out.println(currentPhotoPath);

            //수정
            //str id값이 있으면
            if(str == false) {
                vo.setId(intent.getStringExtra("id"));
                //System.out.println("아이디값"+intent.getStringExtra("id"));
                DiaryDAO.update(dbHelper,vo);
                //System.out.println("업데이트"+vo);
            }else if(str == true) {
                DiaryDAO.insert(dbHelper,vo);
                //System.out.println("인서트"+vo);
            };
            Intent Mainintent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(Mainintent, 2);

        });

        btnImage.setOnClickListener(v->{
            dispatchTakePictureIntent();
        });

        btnPhoto.setOnClickListener(v->{
            getPhoto();
        });

    }

    private void getPhoto(){
        Intent takePictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(takePictureIntent, REQUEST_PHOTO_SELESTION);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
        photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex) {

        }
        if (photoFile != null) {
            photoURI = FileProvider.getUriForFile(this,
                    "com.example.mydiary",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            //}
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageDiary.setImageBitmap(imageBitmap);
        }else if(requestCode ==REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            System.out.println("================test");
            imageDiary.setImageURI(photoURI);
        }else if(requestCode ==REQUEST_PHOTO_SELESTION && resultCode == RESULT_OK){
            Uri selectedImage = data.getData();
            imageDiary.setImageURI(selectedImage);
            currentPhotoPath = selectedImage.toString();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        //getAbsolutePath 절대경로
        currentPhotoPath = image.getAbsolutePath();
        return image;

    }
}
