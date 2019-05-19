package kenneycode.funrenderer.parameter

import android.opengl.GLES30
import kenneycode.funrenderer.uitl.GLUtil

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class Texture2DParameter(key : String, private val value : Int) : Parameter(key) {

    override fun bindUniform(program: Int) {
        if (location < 0) {
            location = GLES30.glGetUniformLocation(program, key)
        }
        GLUtil.checkError()
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0)
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, value)
        GLUtil.checkError()
        GLES30.glUniform1i(location, 0)
        GLUtil.checkError()
    }

}