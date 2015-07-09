package com.zpff.airhockey.Shape;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by zpff on 2015/7/5.
 */
public class LoadDataToNative {
    private static final int BYTE_PER_FLOAT = 4;
    private static final int BYTE_PER_SHORT = 2;
    private  static ByteBuffer vertexData;
    public static FloatBuffer LoadDataFloat(float[] vertice){
        vertexData = ByteBuffer.allocateDirect(vertice.length * BYTE_PER_FLOAT)
                .order(ByteOrder.nativeOrder());
        final FloatBuffer vertexBuffer = vertexData.asFloatBuffer();
        vertexBuffer.put(vertice);
        return vertexBuffer;
    }

    public static ShortBuffer LoadDataShort(short[] vertice){
        vertexData = ByteBuffer.allocateDirect(vertice.length * BYTE_PER_SHORT)
                .order(ByteOrder.nativeOrder());
        final ShortBuffer vertexBuffer = vertexData.asShortBuffer();

        vertexBuffer.put(vertice);
        vertexBuffer.position(0);
        return vertexBuffer;
    }

}
