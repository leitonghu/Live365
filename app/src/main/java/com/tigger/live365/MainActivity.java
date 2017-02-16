package com.tigger.live365;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.tigger.live365.bean.PushBean;

public class MainActivity extends AppCompatActivity {


    private EditText et_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_url = (EditText) findViewById(R.id.et_url);

        findViewById(R.id.push).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadToPush();
            }
        });
        findViewById(R.id.pull).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("key_url",et_url.getText().toString());

                Intent intent = new Intent(MainActivity.this,LivePlayerActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void loadToPush() {
        PushBean pushBean = new PushBean();
        pushBean.setId("1");
        pushBean.setHub("12");
        pushBean.setPublishSecurity("123");
        pushBean.setTitle("live test");
        pushBean.setPublishKey("1234");

        PushBean.HostBean hostBean = pushBean.new HostBean();
        hostBean.setHosts("182.61.54.111");
        pushBean.setHostBean(hostBean);

        PushBean.HostBean.PublishBean publishBean1 = hostBean.new PublishBean();
        publishBean1.setRtmp(et_url.getText().toString());
        hostBean.setPublishBean(publishBean1);

        Gson mGson = new Gson();

        Bundle bundle = new Bundle();
        bundle.putString("key_url",mGson.toJson(pushBean));

        Intent intent = new Intent(MainActivity.this,SWCameraStreamingActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
