package io.github.kenneycode.funrenderer.renderer

import io.github.kenneycode.funrenderer.common.Constants
import io.github.kenneycode.funrenderer.common.Keys
import io.github.kenneycode.funrenderer.io.Input
import io.github.kenneycode.funrenderer.parameter.Mat4Parameter
import io.github.kenneycode.funrenderer.parameter.OESTextureParameter

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class OES2RGBARenderer: SimpleRenderer(Constants.OES_VERTEX_SHADER, Constants.OES_FRAGMENT_SHADER) {

    override fun bindInput(input: Input) {
        this.input = input
        val textures = this.input.getInput()
        setUniform(OESTextureParameter("u_TextureUnit", textures[0]))
    }

    override fun update(data: MutableMap<String, Any>): MutableMap<String, Any> {
        setUniform(Mat4Parameter("u_stMatrix", data[Keys.ST_MATRIX] as FloatArray))
        return data
    }
}