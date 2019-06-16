package io.github.kenneycode.funrenderer.parameter

import android.opengl.GLES30

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class IntParameter(key : String, private val value : Int) : Parameter(key) {

    override fun bindUniform(program: Int) {
        if (location < 0) {
            location = GLES30.glGetAttribLocation(program, key)
        }
        GLES30.glUniform1i(location, value)
    }

}