package com.zzq.paul_tools.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzq.paul_tools.R;
import com.zzq.paul_tools.utils.CrashUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: 点击拍照
 * @Author: zzq
 * @Date: 2020-12-04 10:24
 */
public class TakeCameraActivity extends BaseActivity {


    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.image)
    ImageView image;
    private static final String AUTHORITIES = "com.zzq.paul_tools.fileprovider";

    @Override
    protected int getLayout() {
        return R.layout.activity_take_camera;
    }

    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码
    private static final int CAMERA_REQUEST_CODE = 104;
    private String path;

    @Override
    protected void init() {
        path = CrashUtils.getDirPath(TakeCameraActivity.this).getPath();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCamera();
            }
        });
    }

    /**
     * 去拍照
     */
    private void gotoCamera() {
        if (getPermissions()) {
            startCamera();
        } else {
            //不具有获取权限，需要进行权限申请
            ActivityCompat.requestPermissions(TakeCameraActivity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
        }
    }

    /**
     * 获取权限
     */
    private boolean getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager
                    .PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager
                            .PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    File mCameraFile;

    /**
     * 拍照
     */
    private void startCamera() {
        mCameraFile = new File(path, "img_IdCardMan.png");//照相机的File对象
        Intent intentFromCapture1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
            Uri uriForFile = FileProvider.getUriForFile(TakeCameraActivity.this, AUTHORITIES, mCameraFile);
            intentFromCapture1.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
        } else {
            intentFromCapture1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraFile));
        }
        startActivityForResult(intentFromCapture1, CAMERA_REQUEST_CODE);
    }


    Uri mBloodUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        switch (requestCode) {
            case CAMERA_REQUEST_CODE: //照相后返回
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mBloodUri = FileProvider.getUriForFile(this, AUTHORITIES, mCameraFile);//通过FileProvider创建一个content类型的Uri
                } else {
                    mBloodUri = Uri.fromFile(mCameraFile);
                }
                Glide.with(TakeCameraActivity.this).load(mBloodUri)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.loading_img)
                        .into(image);
                break;
        }
        switch (resultCode) {
            case 1101:
                finish();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == GET_PERMISSION_REQUEST) {
            int size = 0;
            if (grantResults.length >= 1) {
                int writeResult = grantResults[0];
                //读写内存权限
                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
                if (!writeGranted) {
                    size++;
                }
                //录音权限
                int recordPermissionResult = grantResults[1];
                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!recordPermissionGranted) {
                    size++;
                }
                if (size == 0) {
                    startCamera();
                } else {
                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}