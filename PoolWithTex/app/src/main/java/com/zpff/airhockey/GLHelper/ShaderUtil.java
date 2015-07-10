package com.zpff.airhockey.GLHelper;

import android.opengl.GLES20;
import android.util.Log;

/**
 * Created by zpff on 2015/7/5.
 */
public class ShaderUtil {
    private static final String TAG = "ShaderUtil";

    public static int compileVertexShader(String glslCode){
        return compileShader(GLES20.GL_VERTEX_SHADER, glslCode);
    }

    public static int compileFragmentShader(String glslCode){
        return compileShader(GLES20.GL_FRAGMENT_SHADER, glslCode);
    }

    private static int compileShader(int type, String glslCode){
        final int shaderObjectId = GLES20.glCreateShader(type);

        if(shaderObjectId ==0){
            Log.w(TAG,"Could not Create new shader.");
            return 0;
        }

        GLES20.glShaderSource(shaderObjectId, glslCode);
        GLES20.glCompileShader(shaderObjectId);

        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
        Log.i(TAG, "Results of compiling source:" + "\n" + glslCode + "\n:"
                + GLES20.glGetShaderInfoLog(shaderObjectId));

        if(compileStatus[0] == 0){
            GLES20.glDeleteShader(shaderObjectId);
            Log.w(TAG, "Compile shader failed.");
            return 0;
        }

        return shaderObjectId;
    }

    public static int linkProgram(int vertexShader, int fragShader){
        final int programObjectId = GLES20.glCreateProgram();

        if(programObjectId == 0){
            Log.w(TAG, "Could not create new OpenGl Program.");
            return 0;
        }

        GLES20.glAttachShader(programObjectId, vertexShader);
        GLES20.glAttachShader(programObjectId, fragShader);

        GLES20.glLinkProgram(programObjectId);

        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(programObjectId, GLES20.GL_LINK_STATUS, linkStatus, 0);
        Log.i(TAG, "Result of linking Program: "+linkStatus[0]+"\nLog:"+GLES20.glGetProgramInfoLog(programObjectId));

        if(linkStatus[0] == 0){
            GLES20.glDeleteProgram(programObjectId);
            Log.w(TAG,"Linkg Program failed.");
            return 0;
        }
        return programObjectId;
    }

    public static boolean validateProgram(int programObjectId) {
        GLES20.glValidateProgram(programObjectId);

        final int[] validateStatus = new int[1];
        GLES20.glGetProgramiv(programObjectId, GLES20.GL_VALIDATE_STATUS, validateStatus, 0);
        Log.i(TAG, "Results of validating program: " + validateStatus[0]
                + "\nLog:" + GLES20.glGetProgramInfoLog(programObjectId));

        return validateStatus[0] != 0;
    }
}


