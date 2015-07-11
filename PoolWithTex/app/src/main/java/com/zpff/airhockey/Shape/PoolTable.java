package com.zpff.airhockey.Shape;

import android.opengl.GLES20;

import com.zpff.airhockey.GLHelper.TextureUtil;

import java.nio.FloatBuffer;

/**
 * Created by zpff on 2015/7/5.
 */
public class PoolTable {
    final static int COORDS_PER_VERTEX = 2;
    private static final int COORDS_PER_COLOR = 4;
    static float poolTableVertices[] = {
            //triangle fan
             0.00f,  0.0f,
            -0.48f, -0.8f,   // top left
             0.48f, -0.8f,  // bottom left
             0.48f,  0.8f,    // bottom right
            -0.48f,  0.8f,    // top left
            -0.48f, -0.8f,    // bottom right

            -0.48f, -0.64f,
             0.48f, -0.64f,

             0.00f,  0.5f,
    };

    static float tableColorVertices[] ={
            //square color
            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 0.6f, 0.0f, 1.0f,
            0.0f, 0.6f, 0.0f, 1.0f,
            0.0f, 0.6f, 0.0f, 1.0f,
            0.0f, 0.6f, 0.0f, 1.0f,
            0.0f, 0.6f, 0.0f, 1.0f,
            //Lines color
            1.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            //Point color
            1.0f, 1.0f, 1.0f, 1.0f
    };

    private final FloatBuffer vertexBuffer;
    private final FloatBuffer colorBuffer;

    private int mPositionInShader;
    private int mColorInShader;
    private int mProjMatInShader;


    public PoolTable(){
        vertexBuffer = LoadDataToNative.LoadDataFloat(poolTableVertices);
        vertexBuffer.position(0);

        colorBuffer = LoadDataToNative.LoadDataFloat(tableColorVertices);
        colorBuffer.position(0);
    }
    public void draw(int shaderProgram, float[] mvpMatrix){
        mPositionInShader = GLES20.glGetAttribLocation(shaderProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionInShader);
        GLES20.glVertexAttribPointer(mPositionInShader, COORDS_PER_VERTEX, GLES20.GL_FLOAT,
                false, 0, vertexBuffer);

        mColorInShader = GLES20.glGetAttribLocation(shaderProgram, "inColor");
        GLES20.glEnableVertexAttribArray(mColorInShader);
        GLES20.glVertexAttribPointer(mColorInShader, COORDS_PER_COLOR, GLES20.GL_FLOAT,
                false, 0, colorBuffer);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 6);

        //GLES20.glUniform4f(mColorInShader, 1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_LINES, 6, 2);

        //GLES20.glUniform4f(mColorInShader, 1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 8, 1);

        mProjMatInShader = GLES20.glGetUniformLocation(shaderProgram, "modelView");
        GLES20.glUniformMatrix4fv(mProjMatInShader, 1, false, mvpMatrix, 0);
        GLES20.glDisableVertexAttribArray(mPositionInShader);
    }
}
