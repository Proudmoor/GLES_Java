package com.zpff.airhockey.GLHelper;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by zpff on 2015/7/5.
 */
public class ReadShaderFromResource {
    public static String readTextFromResource(Context context, int resourceId){
        StringBuilder glslCode = new StringBuilder();

        try{
            InputStream inputStream = context.getResources().openRawResource(resourceId);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String nextLine;
            while((nextLine = bufferedReader.readLine()) != null){
                glslCode.append(nextLine);
            }
        } catch(IOException e){
            throw new RuntimeException("Could not Open Resource: "+resourceId, e);
        } catch(Resources.NotFoundException nfe){
            throw new RuntimeException("Resource not found: " + resourceId, nfe);
        }

        return glslCode.toString();
    }
}
