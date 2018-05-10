package huconn.yunikim.part5_mission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView;
    Boolean pmCamera = false;
    Boolean pmStorage = false;
    AlertDialog alertDialog;
    File filePath;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.main_content);
        imageView = findViewById(R.id.main_photo_icon);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == imageView) {
            // 권한이 없으면 실행되지 않도록 함.
            if(!checkPermission())
                return;
            selectItem();
        }
        Log.d("로그로그로그", "도착하였습니다.!!!!");
    }

    // 다이알로그 항목 선택시 외부앱 실행 코드
    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Log.d("다이알로그", "선택항목: "+String.valueOf(which));
            if(which == 0) {
                try {
                    String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myApp";
                    File dir = new File(dirPath);
                    if (!dir.exists())
                        dir.mkdir();
                    filePath = File.createTempFile("IMG", ".jpg", dir);
                    if (!filePath.exists())
                        filePath.createNewFile();
                    Log.d("다이알로그", "이미지파일경로: " + filePath.toString());
                    Uri photoURI = FileProvider.getUriForFile(
                            MainActivity.this, BuildConfig.APPLICATION_ID + ".provider", filePath);

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(intent, 100);
                } catch(Exception e) {
                    Log.d("다이알로그", "이미지에러: " + e.toString());
                }
            }
            else if(which == 1) {
                try {
                    String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myApp";
                    File dir = new File(dirPath);
                    if (!dir.exists())
                        dir.mkdir();
                    filePath = File.createTempFile("VIDEO", ".mp4", dir);
                    Log.d("다이알로그", "비디오파일경로: " + filePath.toString());
                    Uri videoURI = FileProvider.getUriForFile(
                            MainActivity.this, BuildConfig.APPLICATION_ID + ".provider", filePath);

                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 7);
                    intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 1024*1024*5);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, videoURI);
                    startActivityForResult(intent, 200);
                } catch(Exception e) {
                    Log.d("다이알로그", "비디오에러: " + e.toString());
                }
            }
        }
    };

    // 외부앱 실행후 되돌아오면 처리하는 코드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            BitmapFactory.Options imgOption = new BitmapFactory.Options();
            imgOption.inSampleSize = 2;     // 1/2 만큼의 사이즈로 줄임
            Bitmap bitmap = BitmapFactory.decodeFile(filePath.getAbsolutePath(), imgOption);

            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(bitmap);
            //imageView.setRotation(90);  // 노트8에서는 90도 틀어져 있다. 왜일까??
            layout.addView(imageView, params);
        }
        else if(requestCode == 200 && resultCode == RESULT_OK) {
            int reqWidth = getResources().getDimensionPixelSize(R.dimen.request_image_width);
            int reqHeight = getResources().getDimensionPixelSize(R.dimen.request_image_height);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(reqWidth, reqHeight);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            VideoView videoView = new VideoView(this);
            videoView.setMediaController(new MediaController(this));
            Uri videoUri = Uri.parse(filePath.getAbsolutePath());
            videoView.setVideoURI(videoUri);

            layout.addView(videoView, params);

            videoView.start();
        }
    }

    private void selectItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("촬영");
        builder.setItems(R.array.selectItem, dialogListener);

        alertDialog = builder.create();
        alertDialog.show();
    }

    private Boolean checkPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED)
            pmCamera = true;
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED)
            pmStorage = true;

        if(!pmStorage || !pmCamera) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.d("로그로그", "결과의 수 : "+String.valueOf(grantResults.length));
        if(requestCode == 100 && grantResults.length > 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                pmCamera = true;
            if(grantResults[1] == PackageManager.PERMISSION_GRANTED)
                pmStorage = true;
        }

        // 모든 권한 획득시 다시 진입.
        if(pmStorage && pmCamera)
            onClick(imageView);
    }
}
