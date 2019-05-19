package kenneycode.funrenderer.io

import kenneycode.funrenderer.common.Ref

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

abstract class Input: Ref() {

    abstract fun getInput(): IntArray

}