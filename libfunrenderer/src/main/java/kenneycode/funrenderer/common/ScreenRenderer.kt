package kenneycode.funrenderer.common

import android.opengl.GLES30
import kenneycode.funrenderer.parameter.FloatArrayParameter

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class ScreenRenderer : SimpleRenderer() {

    override fun initParameter() {
        setAttribute(FloatArrayParameter("a_position", Constants.COMMON_VERTEX_FLIPY))
        setAttribute(FloatArrayParameter("a_textureCoordinate", Constants.COMMON_TEXTURE_COORDINATE))
    }

    override fun bindOutput(width: Int, height: Int) {
        if (outputFrameBuffer == null) {
            outputFrameBuffer = FrameBufferCache.obtainFrameBuffer()
        }
        outputFrameBuffer!!.onBind()
        GLES30.glViewport(0, 0, width, height)
    }

}