package io.github.kenneycode.funrenderer.renderer

import io.github.kenneycode.funrenderer.common.Constants
import io.github.kenneycode.funrenderer.parameter.FloatArrayParameter

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class FlipRenderer : SimpleRenderer() {

    override fun initParameter() {
        setAttribute(FloatArrayParameter("a_position", Constants.COMMON_VERTEX_FLIPY))
        setAttribute(FloatArrayParameter("a_textureCoordinate", Constants.COMMON_TEXTURE_COORDINATE))
    }

}