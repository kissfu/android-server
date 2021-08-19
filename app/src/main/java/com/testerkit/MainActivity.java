package com.testerkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.testerkit.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, ServiceAntiKilled.class));
        //Intent intent=new Intent(MainActivity.this, RTCActivity.class);
        //startActivity(intent);
        finish();
        Log.info("onCreate finish");
    }
}
