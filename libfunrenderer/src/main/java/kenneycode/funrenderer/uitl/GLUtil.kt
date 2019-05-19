package kenneycode.funrenderer.uitl

import android.graphics.Bitmap
import android.opengl.GLES20
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

        fun loadBitmap2Texture(texture : Int, bitmap : Bitmap) {
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture)
            val b = ByteBuffer.allocate(bitmap.width * bitmap.height * 4)
            bitmap.copyPixelsToBuffer(b)
            b.position(0)
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR)
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR)
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE)
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE)
            GLES20.glTexImage2D(
                    GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, bitmap.width,
                    bitmap.height, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, b)
        }
        
        fun texture2Bitmap(texture : Int, width : Int, height : Int) : Bitmap {
            val buffer = ByteBuffer.allocate(width * height * 4)
            val frameBuffers = IntArray(1)
            checkError()
            GLES20.glGenFramebuffers(frameBuffers.size, frameBuffers, 0)
            checkError()
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture)
            checkError()
            GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, frameBuffers[0])
            checkError()
            GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D, texture, 0)
            GLES20.glReadPixels(0, 0, width, height, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, buffer)
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            checkError()
            buffer.position(0)
            bitmap.copyPixelsFromBuffer(buffer)
            GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D, 0, 0)
            checkError()
            GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0)
            GLES20.glDeleteFramebuffers(frameBuffers.size, frameBuffers, 0)
            checkError()
            return bitmap
        }

        fun createTexture() : Int {
            val textures = IntArray(1)
            GLES20.glGenTextures(textures.size, textures, 0)
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textures[0])
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR)
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR)
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE)
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE)
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0)
            return textures[0]
        }

        fun createFrameBuffer(): Int {
            val frameBuffers = IntArray(1)
            GLES30.glGenFramebuffers(frameBuffers.size, frameBuffers, 0)
            return frameBuffers[0]
        }

        fun checkError() {
            val error = GLES20.glGetError()
            if (error != 0) {
                throw RuntimeException("error = $error")
            }
        }



    }

}