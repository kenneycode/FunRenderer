package io.github.kenneycode.funrenderer.renderer

import android.opengl.GLES30
import io.github.kenneycode.funrenderer.common.*
import io.github.kenneycode.funrenderer.io.FrameBuffer
import io.github.kenneycode.funrenderer.io.Input
import io.github.kenneycode.funrenderer.parameter.FloatArrayParameter
import io.github.kenneycode.funrenderer.parameter.Parameter
import io.github.kenneycode.funrenderer.parameter.Texture2DParameter

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

open class SimpleRenderer(vertexShader: String = Constants.COMMON_VERTEX_SHADER, fragmentShader: String = Constants.COMMON_FRAGMENT_SHADER): Renderer {

    private val shader: Shader = Shader(vertexShader, fragmentShader)
    private lateinit var glProgram: GLProgram
    private val attributes = mutableSetOf<Parameter>()
    private val uniforms = mutableSetOf<Parameter>()
    var renderOnInput = false
    var retainOutput = false
    var input: Input?= null
    var outputFrameBuffer : FrameBuffer? = null

    open fun initParameter() {
        setAttribute(FloatArrayParameter("a_position", Constants.COMMON_VERTEX))
        setAttribute(FloatArrayParameter("a_textureCoordinate", Constants.COMMON_TEXTURE_COORDINATE))
    }

    override fun init() : Boolean {
        initParameter()
        glProgram = GLProgramManager.obtainGLProgram(shader)
        return glProgram.init()
    }

    override fun setAttribute(parameter : Parameter) {
        attributes.add(parameter)
    }

    override fun setUniform(parameter : Parameter) {
        uniforms.add(parameter)
    }

    override fun bindInput(input: Input?) {
        this.input = input
        this.input?.let {
            val textures = it.getInput()
            setUniform(Texture2DParameter("u_texture", textures[0]))
        }
    }

    override fun bindParameters() {
        glProgram.bindAttribute(attributes)
        glProgram.bindUniform(uniforms)
    }

    override fun bindOutput(width: Int, height: Int) {
        if (outputFrameBuffer == null) {
            if (renderOnInput) {
                input?.addRef()
                outputFrameBuffer = input as FrameBuffer
            } else {
                input?.let {
                    outputFrameBuffer = FrameBufferCache.obtainFrameBuffer(width, height)
                }

            }
            if (retainOutput) {
                outputFrameBuffer?.addRef()
            }
        }
        outputFrameBuffer?.onBind(width, height)
        GLES30.glViewport(0, 0, width, height)
    }

    override fun unBindInput() {
        input?.releaseRef()
    }

    override fun unBindParameters() {

    }

    override fun unBindOutput() {
        if (!retainOutput && !renderOnInput) {
            outputFrameBuffer?.onUnBind()
        }
    }

    override fun update(data: Any?) {

    }

    override fun render(input: Input?, width: Int, height: Int): FrameBuffer {
        bindInput(input)
        bindParameters()
        bindOutput(width, height)
        performRendering(width, height)
        unBindInput()
        unBindParameters()
        unBindOutput()

        return outputFrameBuffer!!
    }

    override fun performRendering(width: Int, height: Int) {
        GLES30.glClearColor(0.0f, 1.0f, 0.0f, 1.0f)
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT)
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 6)
    }

    override fun release() {
        GLProgramManager.releaseGLProgram(glProgram)
    }


}