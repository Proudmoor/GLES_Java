package com.zpff.airhockey.Shape;

import android.opengl.GLES20;

import java.nio.FloatBuffer;

/**
 * Created by zpff on 2015/7/5.
 */
public class PoolTable {
    final static int COORDS_PER_VERTEX = 2;
    static float squareCoords[] = {
            -0.48f,  0.8f,   // top left
            -0.48f, -0.8f,  // bottom left
             0.48f, -0.8f,    // bottom right

            -0.48f,  0.8f,    // top left
             0.48f, -0.8f,    // bottom right
             0.48f,  0.8f,     // top right

            -0.48f, -0.64f,
             0.48f, -0.64f,

             0.00f,  0.5f,
    };

    private final FloatBuffer vertexBuffer;
    //private final ShortBuffer drawOrderBuffer;

    private int mPositionInShader;
    private int mColorInShader;

    public PoolTable(){
        vertexBuffer = LoadDataToNative.LoadDataFloat(squareCoords);
        vertexBuffer.position(0);

    }
    public void draw(int shaderProgram){
        mPositionInShader = GLES20.glGetAttribLocation(shaderProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionInShader);
        GLES20.glVertexAttribPointer(mPositionInShader, COORDS_PER_VERTEX, GLES20.GL_FLOAT,
                false, 0, vertexBuffer);

        mColorInShader = GLES20.glGetUniformLocation(shaderProgram, "vColor");
        GLES20.glUniform4f(mColorInShader, 0.0f, 1.0f, 0.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);

        GLES20.glUniform4f(mColorInShader, 1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_LINES, 6, 2);

        GLES20.glUniform4f(mColorInShader, 1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 8, 1);
    }
}
