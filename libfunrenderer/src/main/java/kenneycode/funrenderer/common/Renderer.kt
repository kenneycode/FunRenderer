package kenneycode.funrenderer.common

import kenneycode.funrenderer.io.FrameBuffer
import kenneycode.funrenderer.io.Input
import kenneycode.funrenderer.parameter.Parameter

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

interface Renderer {

    fun init() : Boolean
    fun setAttribute(parameter : Parameter)
    fun setUniform(parameter : Parameter)
    fun bindInput(input : Input? = null)
    fun bindParameters()
    fun bindOutput(width : Int, height : Int)
    fun unBindInput()
    fun unBindParameters()
    fun unBindOutput()
    fun update(data : Any? = null)
    fun render(input : Input? = null, width : Int, height : Int) : FrameBuffer
    fun performRendering(width: Int, height: Int)
    fun release()

}