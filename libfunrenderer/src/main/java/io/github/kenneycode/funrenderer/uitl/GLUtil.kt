package io.github.kenneycode.funrenderer.uitl

import android.graphics.Bitmap
import android.opengl.GLES11Ext
import android.opengl.GLES30
import java.nio.ByteBuffer

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/


class GLUtil {

    companion object {

        private val DEBUG = false

        fun loadBitmap2Texture(texture : Int, bitmap : Bitmap) {
            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, texture)
            val b = ByteBuffer.allocate(bitmap.width * bitmap.height * 4)
            bitmap.copyPixelsToBuffer(b)
            b.position(0)
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR)
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR)
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_S, GLES30.GL_CLAMP_TO_EDGE)
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_T, GLES30.GL_CLAMP_TO_EDGE)
            GLES30.glTexImage2D(
                    GLES30.GL_TEXTURE_2D, 0, GLES30.GL_RGBA, bitmap.width,
                    bitmap.height, 0, GLES30.GL_RGBA, GLES30.GL_UNSIGNED_BYTE, b)
        }
        
        fun texture2Bitmap(texture : Int, width : Int, height : Int) : Bitmap {
            val buffer = ByteBuffer.allocate(width * height * 4)
            val frameBuffers = IntArray(1)
            GLES30.glGenFramebuffers(frameBuffers.size, frameBuffers, 0)
            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, texture)
            GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, frameBuffers[0])
            GLES30.glFramebufferTexture2D(GLES30.GL_FRAMEBUFFER, GLES30.GL_COLOR_ATTACHMENT0, GLES30.GL_TEXTURE_2D, texture, 0)
            GLES30.glReadPixels(0, 0, width, height, GLES30.GL_RGBA, GLES30.GL_UNSIGNED_BYTE, buffer)
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            buffer.position(0)
            bitmap.copyPixelsFromBuffer(buffer)
            GLES30.glFramebufferTexture2D(GLES30.GL_FRAMEBUFFER, GLES30.GL_COLOR_ATTACHMENT0, GLES30.GL_TEXTURE_2D, 0, 0)
            GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, 0)
            GLES30.glDeleteFramebuffers(frameBuffers.size, frameBuffers, 0)
            return bitmap
        }

        fun oesTexture2Bitmap(texture: Int, width: Int, height: Int): Bitmap {
            val buffer = ByteBuffer.allocate(width * height * 4)
            val frameBuffers = IntArray(1)
            GLES30.glGenFramebuffers(frameBuffers.size, frameBuffers, 0)
            GLES30.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, texture)
            GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, frameBuffers[0])
            GLES30.glFramebufferTexture2D(GLES30.GL_FRAMEBUFFER, GLES30.GL_COLOR_ATTACHMENT0, GLES11Ext.GL_TEXTURE_EXTERNAL_OES, texture, 0)
            GLES30.glReadPixels(0, 0, width, height, GLES30.GL_RGBA, GLES30.GL_UNSIGNED_BYTE, buffer)
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            buffer.position(0)
            bitmap.copyPixelsFromBuffer(buffer)
            GLES30.glFramebufferTexture2D(GLES30.GL_FRAMEBUFFER, GLES30.GL_COLOR_ATTACHMENT0, GLES11Ext.GL_TEXTURE_EXTERNAL_OES, 0, 0)
            GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, 0)
            GLES30.glDeleteFramebuffers(frameBuffers.size, frameBuffers, 0)
            return bitmap
        }

        fun createTexture() : Int {
            val textures = IntArray(1)
            GLES30.glGenTextures(textures.size, textures, 0)
            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textures[0])
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR)
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR)
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_S, GLES30.GL_CLAMP_TO_EDGE)
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_T, GLES30.GL_CLAMP_TO_EDGE)
            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, 0)
            return textures[0]
        }

        fun createFrameBuffer(): Int {
            val frameBuffers = IntArray(1)
            GLES30.glGenFramebuffers(frameBuffers.size, frameBuffers, 0)
            return frameBuffers[0]
        }

        fun deleteTexture(texture: Int) {
            val textures = IntArray(texture)
            GLES30.glDeleteTextures(textures.size, textures, 0)
        }

        fun deleteFrameBuffer(frameBuffer: Int) {
            val frameBuffers = IntArray(frameBuffer)
            GLES30.glDeleteFramebuffers(frameBuffers.size, frameBuffers, 0)
        }

        fun checkError() {
            if (DEBUG) {
                val error = GLES30.glGetError()
                if (error != 0) {
                    throw RuntimeException("error = $error")
                }
            }
        }



    }

}