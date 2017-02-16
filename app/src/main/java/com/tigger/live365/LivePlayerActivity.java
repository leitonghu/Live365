package com.tigger.live365;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pili.pldroid.player.widget.PLVideoView;

public class LivePlayerActivity extends AppCompatActivity {

    PLVideoView mVideoView;

//    private EditText et_pull_url;
//    private Button startPull;

    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_player);

        mVideoView = (PLVideoView) findViewById(R.id.PLVideoView);

//        et_pull_url = (EditText) findViewById(R.id.et_pull_url);
//        startPull = (Button) findViewById(R.id.start_pull);

//        PLVideoTextureView mVideoView = (PLVideoTextureView) findViewById(R.id.PLVideoTextureView);

        //关联播放控制器
//        MediaController mMediaController = new MediaController(this);
//        mVideoView.setMediaController(mMediaController);


        //设置加载动画
//        View loadingView = findViewById(R.id.LoadingView);
//        mVideoView.setBufferingIndicator(loadingView);

        //设置播放状态监听器

//        mVideoView.setOnPreparedListener(this);
//        mVideoView.setOnInfoListener(this);
//        mVideoView.setOnCompletionListener(this);
//        mVideoView.setOnVideoSizeChangedListener(this);
//        mVideoView.setOnErrorListener(this)

        //设置画面预览模式
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_ORIGIN);
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_FIT_PARENT);
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_16_9);
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_4_3);

        //设置画面旋转
//        mVideoView.setDisplayOrientation(90); // 旋转90度

        //设置播放画面镜像变换
//        mVideoView.setMirror(true);

        //设置播放地址
//        mVideoView.setVideoPath(videoPath);

        if (getIntent() != null){
            url = getIntent().getStringExtra("key_url");

            mVideoView.setVideoPath(url);
        }

        //播放控制
//        mVideoView.start();
//        mVideoView.pause();
//        mVideoView.stopPlayback();
    }














}
