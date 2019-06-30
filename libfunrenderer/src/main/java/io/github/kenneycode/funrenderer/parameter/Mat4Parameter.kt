package io.github.kenneycode.funrenderer.parameter

import android.opengl.GLES30
import io.github.kenneycode.funrenderer.uitl.GLUtil

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class Mat4Parameter(key : String, private val value : FloatArray) : Parameter(key) {

    override fun bindUniform(program: Int) {
        if (location < 0) {
            location = GLES30.glGetUniformLocation(program, key)
        }
        GLUtil.checkError()
        GLES30.glUniformMatrix4fv(location, 1, false, value, 0)
        GLUtil.checkError()
    }

}