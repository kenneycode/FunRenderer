package kenneycode.funrenderer.common

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

open class Ref {

    protected var refCount = 1

    open fun addRef(count: Int = 1) {
        refCount += count
    }

    open fun releaseRef() {
        --refCount
    }

}