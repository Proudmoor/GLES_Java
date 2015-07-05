package com.zpff.airhockey;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;

import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends Activity {
    private GLSurfaceView mGLView;
    private boolean       mRendererSet = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGLView = new GLSurfaceView(this);

        final ActivityManager avManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configInfo = avManager.getDeviceConfigurationInfo();
        final boolean supportGLES2 = configInfo.reqGlEsVersion >= 0x20000;

        if(supportGLES2){
            mGLView.setEGLContextClientVersion(2);

            mGLView.setRenderer(new MyRenderer(this));
            mRendererSet = true;
        } else{
            Toast.makeText(this, "Device cannot support OpenGL ES 2.0.",Toast.LENGTH_SHORT).show();
            return ;
        }

        setContentView(mGLView);
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(mRendererSet){
            mGLView.onPause();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(mRendererSet){
            mGLView.onResume();
        }
    }

}
