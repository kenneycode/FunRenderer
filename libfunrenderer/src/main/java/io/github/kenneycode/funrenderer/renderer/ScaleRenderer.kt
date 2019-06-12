package io.github.kenneycode.funrenderer.renderer

import android.opengl.GLES30
import io.github.kenneycode.funrenderer.common.Constants
import io.github.kenneycode.funrenderer.common.FrameBufferCache
import io.github.kenneycode.funrenderer.parameter.FloatArrayParameter

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class ScaleRenderer : SimpleRenderer() {

    private var scaledWidth = 0
    private var scaledHeight = 0

    fun setScaledSize(width: Int, height: Int) {
        scaledWidth = width
        scaledHeight = height
    }

    override fun bindOutput(width: Int, height: Int) {
        if (outputFrameBuffer == null) {
            outputFrameBuffer = FrameBufferCache.obtainFrameBuffer(scaledWidth, scaledHeight)
        }
        outputFrameBuffer!!.onBind(scaledWidth, scaledHeight)
        GLES30.glViewport(0, 0, scaledWidth, scaledHeight)
    }

}