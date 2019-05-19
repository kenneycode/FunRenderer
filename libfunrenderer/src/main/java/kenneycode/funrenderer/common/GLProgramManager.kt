package kenneycode.funrenderer.common

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

object GLProgramManager {

    private val cache = mutableMapOf<Shader, GLProgram>()

    fun obtainGLProgram(shader: Shader): GLProgram {
        if (!cache.contains(shader)) {
            cache[shader] = GLProgram(shader)
        } else {
            cache[shader]!!.addRef()
        }
        return cache[shader]!!
    }

    fun releaseGLProgram() {

    }

}