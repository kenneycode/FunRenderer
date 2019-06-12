package io.github.kenneycode.funrenderer.io

import io.github.kenneycode.funrenderer.common.Ref

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

abstract class Input: Ref() {

    var width = 0
    var height = 0
    abstract fun getInput(): IntArray
    open fun release() {}

}