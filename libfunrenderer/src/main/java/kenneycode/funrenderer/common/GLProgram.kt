package kenneycode.funrenderer.common

import android.opengl.GLES30
import kenneycode.funrenderer.parameter.Parameter

class GLProgram(private val shader: Shader) : Ref() {

    private var program = 0

    fun init(): Boolean {
        program = GLES30.glCreateProgram()

        val vertexShader = GLES30.glCreateShader(GLES30.GL_VERTEX_SHADER)
        val fragmentShader= GLES30.glCreateShader(GLES30.GL_FRAGMENT_SHADER)

        GLES30.glShaderSource(vertexShader, shader.vertexShader)
        GLES30.glShaderSource(fragmentShader, shader.fragmentShader)

        GLES30.glCompileShader(vertexShader)
        GLES30.glCompileShader(fragmentShader)

        GLES30.glAttachShader(program, vertexShader)
        GLES30.glAttachShader(program, fragmentShader)

        GLES30.glLinkProgram(program)

        return program != 0
    }

    fun bindAttribute(attributes: Set<Parameter>) {
        GLES30.glUseProgram(program)
        attributes.forEach { attribute ->
            attribute.bindAttribute(program)
        }
    }

    fun bindUniform(uniforms: Set<Parameter>) {
        GLES30.glUseProgram(program)
        uniforms.forEach { uniform ->
            uniform.bindUniform(program)
        }
    }

}