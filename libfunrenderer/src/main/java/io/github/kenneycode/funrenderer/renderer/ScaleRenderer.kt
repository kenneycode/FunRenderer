package io.github.kenneycode.funrenderer.renderer

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class ScaleRenderer : SimpleRenderer() {

    private var scaledWidth = 0
    private var scaledHeight = 0

    fun setScaledSize(scaledWidth: Int, scaledHeight: Int) {
        this.scaledWidth = scaledWidth
        this.scaledHeight = scaledHeight
    }

    override fun bindOutput(width: Int, height: Int) {
        super.bindOutput(scaledWidth, scaledHeight)
    }
}