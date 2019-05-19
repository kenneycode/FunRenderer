package kenneycode.funrenderer.io

import android.opengl.GLES30
import kenneycode.funrenderer.uitl.GLUtil
import kenneycode.funrenderer.common.FrameBufferCache

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

open class FrameBuffer(var texture: Int = 0, width : Int = 0, height : Int = 0, var autoRecycle : Boolean = true) : Output() {

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
        if (width != 0 && height != 0 && frameBuffer == 0) {
            if (frameBuffer == 0) {
                frameBuffer = GLUtil.createFrameBuffer()
                texture = if (texture == 0) { GLUtil.createTexture() } else { texture }
            }
            if (width != this.width || height != this.height) {
                GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, frameBuffer)
                GLES30.glFramebufferTexture2D(GLES30.GL_FRAMEBUFFER, GLES30.GL_COLOR_ATTACHMENT0, GLES30.GL_TEXTURE_2D, texture, 0)
                GLES30.glTexImage2D(GLES30.GL_TEXTURE_2D, 0, GLES30.GL_RGBA, width, height, 0, GLES30.GL_RGBA, GLES30.GL_UNSIGNED_BYTE, null)
            }
        }
        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, frameBuffer)
    }

    private fun unBind() {
        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, 0)
    }

    override fun releaseRef() {
        if (autoRecycle) {
            super.releaseRef()
            if (refCount == 0) {
                FrameBufferCache.releaseFrameBuffer(this)
            }
        }
    }

}