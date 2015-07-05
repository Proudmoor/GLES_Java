package com.zpff.airhockey;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

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
    private PoolTable mPooltable;
    public MyRenderer(Context context){
        this.mContext = context;
    }
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        GLES20.glClearColor(0.0f, 0.5f, 0.5f, 1.0f);
        loadShader();

        mPooltable = new PoolTable();
    }

    public void onSurfaceChanged(GL10 gl, int width, int height){
        GLES20.glViewport(0, 0, width, height);
    }

    public void onDrawFrame(GL10 gl){
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        mPooltable.draw(mProgram);
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
