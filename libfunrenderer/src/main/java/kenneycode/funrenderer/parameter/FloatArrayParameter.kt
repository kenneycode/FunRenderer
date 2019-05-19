package kenneycode.funrenderer.parameter

import android.opengl.GLES30
import kenneycode.funrenderer.uitl.GLUtil
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class FloatArrayParameter(key : String, private val value : FloatArray, private val componentCount : Int = 2) : Parameter(key) {

    override fun bindAttribute(program : Int) {
        if (location < 0) {
            location = GLES30.glGetAttribLocation(program, key)
        }
        val buffer = ByteBuffer.allocateDirect(value.size * java.lang.Float.SIZE)
                        .order(ByteOrder.nativeOrder())
                        .asFloatBuffer()
                        .put(value)
                        .position(0)
        GLES30.glVertexAttribPointer(location, componentCount, GLES30.GL_FLOAT, false, 0, buffer)
        GLUtil.checkError()
        GLES30.glEnableVertexAttribArray(location)
        GLUtil.checkError()
    }

}