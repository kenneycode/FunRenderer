package kenneycode.funrenderer.parameter

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

open class Parameter(protected val key : String) {

    protected var location = -1

    open fun bindAttribute(program : Int) {}
    open fun bindUniform(program : Int) {}

}