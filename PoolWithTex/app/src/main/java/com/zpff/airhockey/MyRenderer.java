package com.zpff.airhockey;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.zpff.airhockey.GLHelper.ReadShaderFromResource;
import com.zpff.airhockey.GLHelper.ShaderUtil;
import com.zpff.airhockey.Shape.PoolTable;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zpf on 2015/7/4.
 */
public class MyRenderer implements GLSurfaceView.Renderer {
    private final Context mContext;

    private int mProgram;
    private PoolTable mPoolTable;
    private float[] mProjectionMatrix = new float[16];
    private float[] mViewMatrix = new float[16];
    private float[] mMVPMatrix = new float[16];

    public MyRenderer(Context context){
        this.mContext = context;
    }
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        GLES20.glClearColor(0.0f, 0.5f, 0.5f, 1.0f);
        loadShader();
        mPoolTable = new PoolTable();
    }

    public void onSurfaceChanged(GL10 gl, int width, int height){
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width/ height;

        Matrix.orthoM(mProjectionMatrix, 0, -ratio, ratio, -1.0f ,1.0f ,-1.0f, 1.0f);
        //Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    public void onDrawFrame(GL10 gl){
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
/*
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);*/
        mPoolTable.draw(mProgram, mProjectionMatrix);
    }


    private void loadShader(){
        String vertexGLSLCode = ReadShaderFromResource.readTextFromResource(mContext,R.raw.vshader);
        String fragGLSLCode   = ReadShaderFromResource.readTextFromResource(mContext, R.raw.fshader);

        int vertexShader = ShaderUtil.compileVertexShader(vertexGLSLCode);
        int fragShader   = ShaderUtil.compileFragmentShader(fragGLSLCode);

        mProgram = ShaderUtil.linkProgram(vertexShader, fragShader);
        ShaderUtil.validateProgram(mProgram);
        GLES20.glUseProgram(mProgram);
    }
}
