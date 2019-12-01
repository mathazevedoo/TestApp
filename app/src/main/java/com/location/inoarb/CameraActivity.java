package com.location.inoarb;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.FileProvider;

import com.location.inoarb.builder.impl.InoarbImageDataClobBuilder;
import com.location.inoarb.session.SessionAccess;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class CameraActivity extends AppCompatActivity {

    private Button btnTakePic,btnConfirmar;
    private ImageView imageView;
    private String pathToFile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btnTakePic = findViewById(R.id.btnTakePic);
        if(Build.VERSION.SDK_INT >= 23){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }
        btnTakePic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dispatchPictureTakeAction();
            }

        });
        imageView = findViewById(R.id.image);
        btnConfirmar = findViewById(R.id.btnConfirmar);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 1) {
                InoarbImageDataClobBuilder inoarbImageDataClobBuilder = new InoarbImageDataClobBuilder();
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);

                SessionAccess.getSessionSingleton().save("encodedImageClob",
                        inoarbImageDataClobBuilder.build(new File(pathToFile)));

                Log.d("mylog", "Clob 1-->"+inoarbImageDataClobBuilder.build(new File(pathToFile)));
                Log.d("mylog", "Clob 2-->"+SessionAccess.getSessionSingleton().get("encodedImageClob"));


                imageView.setImageBitmap(bitmap);
              //  proxlayout();
                btnConfirmar.setVisibility(View.VISIBLE);
            }
        }
    }
    private void dispatchPictureTakeAction(){
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePic.resolveActivity(getPackageManager())!= null){
            File photoFile = null;
            photoFile = createPhotoFile();
            if (photoFile != null){
                pathToFile = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(CameraActivity.this,"com.location.inoarb.fileprovider",photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePic, 1);
            }
        }
    }
    private File createPhotoFile(){
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        // File image = File.createTempFile(name, ".jpg",storageDir);
        try {
            image = File.createTempFile(name, ".jpg", storageDir);
        }catch (IOException e){
            Log.d("mylog", "Excep : " + e.toString()) ;
        }
        return image;
    }
    public void proxlayout(View view){
        Intent intent = new Intent(this,com.location.inoarb.LocationActivity.class);
        ActivityOptionsCompat activityOptionCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.mover_direita);
        ActivityCompat.startActivity(this, intent, activityOptionCompat.toBundle());
        //startActivity(new Intent(this,com.location.inoarb.LocationActivity.class));
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.mover_esquerda, R.anim.fade_out);
    }
}
