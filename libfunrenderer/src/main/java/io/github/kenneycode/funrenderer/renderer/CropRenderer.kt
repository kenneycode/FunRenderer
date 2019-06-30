package io.github.kenneycode.funrenderer.renderer

import io.github.kenneycode.funrenderer.common.Keys
import io.github.kenneycode.funrenderer.common.Size
import io.github.kenneycode.funrenderer.parameter.FloatArrayParameter

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class CropRenderer : SimpleRenderer() {

    override fun update(data: MutableMap<String, Any>): MutableMap<String, Any> {
        data[Keys.INPUT_SIZE]?.let { it ->
            val inputSize = it as Size
            val inputRatio = inputSize.width.toFloat() / inputSize.height
            data[Keys.CROP_RATIO]?.let {
                val cropRatio = it as Float
                setAttribute(FloatArrayParameter("a_textureCoordinate", getCroppedTexCoords(inputRatio, cropRatio)))
            }
        }
        return data
    }

    private fun getCroppedTexCoords(inputRatio: Float, cropRatio: Float): FloatArray {
        var left = 0f
        var top = 0f
        var right = 0f
        var bottom = 0f
        if (inputRatio < cropRatio) {
            left = 0f
            right = 1f
            bottom = (1 - inputRatio / cropRatio) / 2
            top = bottom + inputRatio / cropRatio
        } else {
            bottom = 0f
            top = 1f
            left = (1 - cropRatio / inputRatio) / 2
            right = left + cropRatio / inputRatio
        }
        return floatArrayOf(left, bottom, left, top, right, top, left, bottom, right, top, right, bottom)
    }

}