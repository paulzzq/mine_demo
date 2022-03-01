package com.zzq.paul_tools.activity;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.zzq.paul_tools.R;
import com.zzq.paul_tools.mode.GoogleFaceDetect;
import com.zzq.paul_tools.utils.DisplayUtil;
import com.zzq.paul_tools.utils.EventUtil;
import com.zzq.paul_tools.view.FaceView;
import com.zzq.paul_tools.view.camera.CameraInterface;
import com.zzq.paul_tools.view.camera.preview.CameraSurfaceView;

public class CheckFaceActivity extends BaseActivity {
    private static final String TAG = "yanzi";
    CameraSurfaceView surfaceView = null;
    ImageButton shutterBtn;
    ImageButton switchBtn;
    FaceView faceView;
    float previewRate = -1f;
    private MainHandler mMainHandler = null;
    GoogleFaceDetect googleFaceDetect = null;

    @Override
    protected int getLayout() {
        return R.layout.activity_check_face;
    }

    @Override
    protected void init() {
        initView();
        initData();
    }

    public void initData() {
        mMainHandler = new MainHandler();
        googleFaceDetect = new GoogleFaceDetect(getApplicationContext(), mMainHandler);
        shutterBtn.setOnClickListener(new BtnListeners());
        switchBtn.setOnClickListener(new BtnListeners());
        mMainHandler.sendEmptyMessageDelayed(EventUtil.CAMERA_HAS_STARTED_PREVIEW, 1500);
    }

    public void initView() {
        surfaceView = (CameraSurfaceView)findViewById(R.id.camera_surfaceview);
        shutterBtn = (ImageButton)findViewById(R.id.btn_shutter);
        switchBtn = (ImageButton)findViewById(R.id.btn_switch);
        faceView = (FaceView)findViewById(R.id.face_view);
        initViewParams();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.camera, menu);
        return true;
    }
    private class BtnListeners implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_shutter:
                    takePicture();
                    break;
                case R.id.btn_switch:
                    switchCamera();
                    break;
                default:break;
            }
        }
    }

    private void initViewParams(){
        ViewGroup.LayoutParams params = surfaceView.getLayoutParams();
        Point p = DisplayUtil.getScreenMetrics(this);
        params.width = p.x;
        params.height = p.y;
        previewRate = DisplayUtil.getScreenRate(this);
        surfaceView.setLayoutParams(params);
    }
    private  class MainHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case EventUtil.UPDATE_FACE_RECT:
                    Camera.Face[] faces = (Camera.Face[]) msg.obj;
                    faceView.setFaces(faces);
                    break;
                case EventUtil.CAMERA_HAS_STARTED_PREVIEW:
                    startGoogleFaceDetect();
                    break;
            }
            super.handleMessage(msg);
        }

    }

    private void takePicture(){
        CameraInterface.getInstance().doTakePicture();
        mMainHandler.sendEmptyMessageDelayed(EventUtil.CAMERA_HAS_STARTED_PREVIEW, 1500);
    }
    private void switchCamera(){
        stopGoogleFaceDetect();
        int newId = (CameraInterface.getInstance().getCameraId() + 1)%2;
        CameraInterface.getInstance().doStopCamera();
        CameraInterface.getInstance().doOpenCamera(null, newId);
        CameraInterface.getInstance().doStartPreview(surfaceView.getSurfaceHolder(), previewRate);
        mMainHandler.sendEmptyMessageDelayed(EventUtil.CAMERA_HAS_STARTED_PREVIEW, 1500);
    }
    private void startGoogleFaceDetect(){
        Camera.Parameters params = CameraInterface.getInstance().getCameraParams();
        if(params.getMaxNumDetectedFaces() > 0){
            if(faceView != null){
                faceView.clearFaces();
                faceView.setVisibility(View.VISIBLE);
            }
            CameraInterface.getInstance().getCameraDevice().setFaceDetectionListener(googleFaceDetect);
            CameraInterface.getInstance().getCameraDevice().startFaceDetection();
        }
    }
    private void stopGoogleFaceDetect(){
        Camera.Parameters params = CameraInterface.getInstance().getCameraParams();
        if(params.getMaxNumDetectedFaces() > 0){
            CameraInterface.getInstance().getCameraDevice().setFaceDetectionListener(null);
            CameraInterface.getInstance().getCameraDevice().stopFaceDetection();
            faceView.clearFaces();
        }
    }
}
