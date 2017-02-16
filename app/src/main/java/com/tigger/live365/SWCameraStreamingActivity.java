package com.tigger.live365;

import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;
import com.qiniu.pili.droid.streaming.MediaStreamingManager;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.StreamingStateChangedListener;
import com.qiniu.pili.droid.streaming.widget.AspectFrameLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class SWCameraStreamingActivity extends AppCompatActivity implements StreamingStateChangedListener {


    private JSONObject mJSONObject;
    private MediaStreamingManager mMediaStreamingManager;
    private StreamingProfile mProfile;


    String streamJsonStrFromServer;

    private AspectFrameLayout afl;
    private GLSurfaceView glSurfaceView;

    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swcamera_streaming);

        if (getIntent() != null){
            streamJsonStrFromServer = getIntent().getStringExtra("key_url");
        }


        afl = (AspectFrameLayout) findViewById(R.id.cameraPreview_afl);

        // Decide FULL screen or real size
        afl.setShowMode(AspectFrameLayout.SHOW_MODE.FULL);
        glSurfaceView = (GLSurfaceView) findViewById(R.id.cameraPreview_surfaceView);


        try {
            mJSONObject = new JSONObject(streamJsonStrFromServer);
            StreamingProfile.Stream stream = new StreamingProfile.Stream(mJSONObject);

            mProfile = new StreamingProfile();

           /* try {
//                mProfile.setPublishUrl(streamJsonStrFromServer);
//                mProfile.setPublishUrl(streamJsonStrFromServer.substring(Config.EXTRA_PUBLISH_URL_PREFIX.length()));
//                mProfile.setPublishUrl(streamJsonStrFromServer);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }*/

            streamJsonStrFromServer = "";
            mProfile.setVideoQuality(StreamingProfile.VIDEO_QUALITY_HIGH1)
                    .setAudioQuality(StreamingProfile.AUDIO_QUALITY_MEDIUM2)
                    .setEncodingSizeLevel(StreamingProfile.VIDEO_ENCODING_HEIGHT_480)
                    .setEncoderRCMode(StreamingProfile.EncoderRCModes.QUALITY_PRIORITY)
                    .setStream(stream);  // You can invoke this before startStreaming, but not in initialization phase.
//                    .setPublishUrl(streamJsonStrFromServer);

            CameraStreamingSetting setting = new CameraStreamingSetting();
            setting.setCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT)
                    .setContinuousFocusModeEnabled(true)
                    .setCameraPrvSizeLevel(CameraStreamingSetting.PREVIEW_SIZE_LEVEL.MEDIUM)
                    .setCameraPrvSizeRatio(CameraStreamingSetting.PREVIEW_SIZE_RATIO.RATIO_16_9);

            mMediaStreamingManager = new MediaStreamingManager(this, afl, glSurfaceView, AVCodecType.SW_VIDEO_WITH_SW_AUDIO_CODEC);  // soft codec

            mMediaStreamingManager.prepare(setting, mProfile);
            mMediaStreamingManager.setStreamingStateListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        mMediaStreamingManager.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // You must invoke pause here.
        mMediaStreamingManager.pause();
    }

    @Override
    public void onStateChanged(StreamingState streamingState, Object extra) {

        switch (streamingState) {
            case PREPARING:
                break;
            case READY:
                // start streaming when READY
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (mMediaStreamingManager != null) {
                            mMediaStreamingManager.startStreaming();
                        }
                    }
                }).start();
                break;
            case CONNECTING:

                break;
            case STREAMING:
                // The av packet had been sent.
                Log.e("swift","====推流====");
                break;
            case SHUTDOWN:
                // The streaming had been finished.
                break;
            case IOERROR:
                // Network connect error.
                break;
            case OPEN_CAMERA_FAIL:
                // Failed to open camera.
                break;
            case DISCONNECTED:
                // The socket is broken while streaming
                break;
        }
    }
}
