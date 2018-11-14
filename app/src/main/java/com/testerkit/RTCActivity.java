package com.testerkit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;


import com.testerkit.uia.utils.Constants;
import com.testerkit.uia.utils.Logger;
import com.testerkit.screen.PeerConnectionClient;
import com.testerkit.screen.WebRtcClient;
import com.testerkit.screen.WebRtcClient.RtcListener;

import org.webrtc.ScreenCapturerAndroid;
import org.webrtc.VideoCapturer;



public class RTCActivity extends Activity implements RtcListener{

    String FUNC = "RTC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        report("onCreate");
        startScreenCapture();
    }


    public static final int SCREEN_RESOLUTION_SCALE = 2;
    private static final int CAPTURE_PERMISSION_REQUEST_CODE = 1;
    private static Intent mMediaProjectionPermissionResultData;
    private static int mMediaProjectionPermissionResultCode;
    WebRtcClient mWebRtcClient;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private  void startScreenCapture(){
        if (Constants.API_LEVEL() < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) getApplication().getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), CAPTURE_PERMISSION_REQUEST_CODE);
        report("startScreenCapture");
    }
    public void report(String info) {
        Logger.info("RTC",info);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private VideoCapturer createScreenCapturer() {
        if (mMediaProjectionPermissionResultCode != Activity.RESULT_OK) {
            report("User didn't give permission to capture the screen.");
            return null;
        }
        return new ScreenCapturerAndroid(
                mMediaProjectionPermissionResultData, new MediaProjection.Callback() {
            @Override
            public void onStop() {
                report("User revoked permission to capture the screen.");
            }
        });
    }
    private void init(){
        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);

        PeerConnectionClient.PeerConnectionParameters params = new PeerConnectionClient.PeerConnectionParameters(
                true, false, true,displaySize.x/SCREEN_RESOLUTION_SCALE, displaySize.y/SCREEN_RESOLUTION_SCALE, 0, 0, "VP8",false,
                true, 0, "OPUS", false, false, false, false, false, false, false, false, null);
        mWebRtcClient = new WebRtcClient(getApplicationContext(), this, createScreenCapturer(), params);


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.iFunc(FUNC,"startScreenCapture,",requestCode,resultCode,",",data);
        if (requestCode != CAPTURE_PERMISSION_REQUEST_CODE)
            return;
        mMediaProjectionPermissionResultCode = resultCode;
        mMediaProjectionPermissionResultData = data;
        init();
    }


    public static String STREAM_NAME_PREFIX = "android_device_stream";
    //
    @Override
    public void onReady(String remoteId) {
        mWebRtcClient.start(STREAM_NAME_PREFIX);
    }

    @Override
    public void onStatusChanged(final String newStatus) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), newStatus, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
