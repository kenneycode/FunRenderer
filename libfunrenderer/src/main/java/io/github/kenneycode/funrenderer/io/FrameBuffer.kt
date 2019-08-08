package io.github.kenneycode.funrenderer.io

import android.opengl.GLES30
import io.github.kenneycode.funrenderer.common.FrameBufferCache
import io.github.kenneycode.funrenderer.uitl.GLUtil

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

open class FrameBuffer(var texture: Int = 0, width : Int = 0, height : Int = 0, private var autoRecycle : Boolean = true) : Output(width, height) {

    var frameBuffer = 0

    override fun getInput(): IntArray {
        return intArrayOf(texture)
    }

    override fun onBind(width: Int, height: Int) {
        bind(width, height)
    }

    override fun onUnBind() {
        unBind()
    }

    private fun bind(width: Int = 0, height: Int = 0) {
        if (width != 0 && height != 0) {
            if (frameBuffer == 0) {
                frameBuffer = GLUtil.createFrameBuffer()
                texture = if (texture == 0) { GLUtil.createTexture() } else { texture }
            }
            if (width != this.width || height != this.height) {
                this.width = width
                this.height = height
                GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, texture)
                GLES30.glTexImage2D(GLES30.GL_TEXTURE_2D, 0, GLES30.GL_RGBA, width, height, 0, GLES30.GL_RGBA, GLES30.GL_UNSIGNED_BYTE, null)
                GLUtil.checkError()
                GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, frameBuffer)
                GLUtil.checkError()
                GLES30.glFramebufferTexture2D(GLES30.GL_FRAMEBUFFER, GLES30.GL_COLOR_ATTACHMENT0, GLES30.GL_TEXTURE_2D, texture, 0)
                GLUtil.checkError()
            }
        }
        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, frameBuffer)
    }

    private fun unBind() {
        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, 0)
    }

    override fun release() {
        GLUtil.deleteTexture(texture)
        GLUtil.deleteFrameBuffer(frameBuffer)
    }

    override fun releaseRef(): Boolean {
        var ret = false
        if (autoRecycle) {
            ret = super.releaseRef()
            if (ret) {
                FrameBufferCache.releaseFrameBuffer(this)
            }
        }
        return ret
    }

}