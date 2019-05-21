package io.github.kenneycode.funrenderer.common

import io.github.kenneycode.funrenderer.io.FrameBuffer
import io.github.kenneycode.funrenderer.io.Input
import io.github.kenneycode.funrenderer.renderer.Renderer

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class RenderChain {

    private val renderers = mutableListOf<Renderer>()

    companion object {

        fun create() : RenderChain {
            return RenderChain()
        }

    }

    fun init() {
        renderers.forEach { renderer ->
            renderer.init()
        }
    }

    fun addRenderer(renderer: Renderer): RenderChain {
        renderers.add(renderer)
        return this
    }

    fun update(data : Any?) {
        renderers.forEach { renderer ->
            renderer.update(data)
        }
    }

    fun render(input : Input, width : Int = 0, height : Int = 0): FrameBuffer? {
        var ip: Input?= input
        var output: FrameBuffer ?= null
        renderers.forEach { renderer ->
            output = renderer.render(ip, width, height)
            ip = output
        }
        return output
    }

}