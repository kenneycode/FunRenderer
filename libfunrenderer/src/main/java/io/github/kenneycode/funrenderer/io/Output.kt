package io.github.kenneycode.funrenderer.io

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

abstract class Output: Input() {

    abstract fun onBind(width: Int = 0, height: Int = 0)
    abstract fun onUnBind()

}