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

    fun render(input : Input, data: MutableMap<String, Any> = mutableMapOf()): FrameBuffer? {
        var ip: Input? = input
        var d = data
        var output: FrameBuffer ?= null
        renderers.forEach { renderer ->
            d[Keys.INPUT_SIZE] = Size(ip!!.width, ip!!.height)
            d = renderer.update(d)
            output = renderer.render(ip!!, ip!!.width, ip!!.height)
            ip = output
        }
        return output
    }

    fun release() {
        renderers.forEach { renderer ->
            renderer.release()
        }
    }

}