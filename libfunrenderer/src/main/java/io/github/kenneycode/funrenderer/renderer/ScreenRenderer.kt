package io.github.kenneycode.funrenderer.renderer

import android.opengl.GLES30
import io.github.kenneycode.funrenderer.common.FrameBufferCache
import io.github.kenneycode.funrenderer.common.Keys
import io.github.kenneycode.funrenderer.common.Size

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class ScreenRenderer : SimpleRenderer() {

    private var surfaceSize: Size? = null

    override fun update(data: MutableMap<String, Any>): MutableMap<String, Any> {
        data[Keys.SURFACE_WIDTH]?.let { width ->
            data[Keys.SURFACE_HEIGHT]?.let { height ->
                surfaceSize = Size(width as Int, height as Int)
            }
        }
        return super.update(data)
    }

    override fun bindOutput(width: Int, height: Int) {
        var outputWidth = width
        var outputHeight = height
        surfaceSize?.let {
            outputWidth = it.width
            outputHeight = it.height
        }
        if (outputFrameBuffer == null) {
            outputFrameBuffer = FrameBufferCache.obtainFrameBuffer()
        }
        outputFrameBuffer!!.onBind()
        GLES30.glViewport(0, 0, outputWidth, outputHeight)
    }

}