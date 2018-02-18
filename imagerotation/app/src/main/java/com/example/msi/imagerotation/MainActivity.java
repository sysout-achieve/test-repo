package com.example.msi.imagerotation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {



//    public static int getImageOrientation(String path){
//        int rotation = 0;
//        try{
//            ExifInterface exif = new ExifInterface(path);
//            int rot = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
//            if(rot == ExifInterface.ORIENTATION_ROTATE_90){
//                rotation = 90;
//            } else if(rot == ExifInterface.ORIENTATION_ROTATE_180){
//                rotation = 180;
//            } else if(rot == ExifInterface.ORIENTATION_ROTATE_270){
//                rotation = 270;
//            } else {
//                rotation = 0;
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//        return  rotation;
//    }
//
//    public static Bitmap imgRotate(Bitmap bmp, int orientation){
//        int width = bmp.getWidth();
//        int height = bmp.getHeight();
//        Matrix matrix = new Matrix();
//        matrix.postRotate(orientation);
//
//        Bitmap resizedBitmap = Bitmap.createBitmap(bmp,0,0,width,height,matrix,true);
//        bmp.recycle();
//        return resizedBitmap;
//    }

    ImageView example;
    int mDegree = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDegree = mDegree+90;
                example = (ImageView) findViewById(R.id.example);
                example.setImageBitmap(rotateImg(BitmapFactory.decodeResource(getResources(),(R.drawable.img_example)),mDegree));

            }
        });
    }

    public Bitmap rotateImg(Bitmap bitmap, float degree){
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0,0, bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }
}

//    public synchronized static int GetExifOrientation(String filepath){
//        int degree = 0;
//        ExifInterface exif = null;
//        try{
//            exif = new ExifInterface(filepath);
//        }catch (IOException e) {
//
//        }
//        if(exif != null){
//            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
//            if(orientation != -1){
//                switch (orientation){
//                    case  ExifInterface.ORIENTATION_ROTATE_90:
//                    degree = 90;
//                    break;
//
//                    case  ExifInterface.ORIENTATION_ROTATE_180:
//                    degree = 180;
//                    break;
//
//                    case  ExifInterface.ORIENTATION_ROTATE_270:
//                    degree = 270;
//                    break;
//                }
//            }
//        }
//        return  degree;
//    }
//
//    public synchronized static Bitmap GetRotatedBitmap(Bitmap bitmap, int degrees) {
//        if (degrees != 0 && bitmap != null){
//            Matrix m = new Matrix();
//            m.setRotate(degrees, (float) bitmap.getWidth()/2, (float)bitmap.getHeight()/2);
//            try{
//                Bitmap b2 = Bitmap.createBitmap(bitmap,0,0, bitmap.getWidth(),bitmap.getHeight(),m,true);
//                if(bitmap != b2){
//                    bitmap.recycle();
//                    bitmap= b2;
//                }
//            } catch (OutOfMemoryError ex){
//
//            }
//        }
//        return bitmap;
//    }





