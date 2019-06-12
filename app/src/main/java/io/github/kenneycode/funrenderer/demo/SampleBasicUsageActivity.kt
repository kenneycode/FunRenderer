package io.github.kenneycode.funrenderer.demo

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import io.github.kenneycode.funrenderer.io.Input
import io.github.kenneycode.funrenderer.io.Texture
import io.github.kenneycode.funrenderer.renderer.Renderer
import io.github.kenneycode.funrenderer.renderer.ScreenRenderer
import io.github.kenneycode.funrenderer.uitl.FileUtil
import io.github.kenneycode.funrenderer.uitl.GLUtil
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class SampleBasicUsageActivity : Activity() {

    private lateinit var renderer: Renderer
    private lateinit var input: Input

    private var glSurfaceViewWidth = 0
    private var glSurfaceViewHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_basic_usage)
        val glSurfaceView = findViewById<GLSurfaceView>(R.id.glSurfaceView)
        glSurfaceView.setEGLContextClientVersion(3)
        glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 0, 0)
        glSurfaceView.setRenderer(MyRenderer())

    }

    override fun onDestroy() {
        super.onDestroy()
        input.release()
        renderer.release()
    }

    inner class MyRenderer : GLSurfaceView.Renderer {

        override fun onDrawFrame(gl: GL10?) {
            renderer.render(input, glSurfaceViewWidth, glSurfaceViewHeight)
        }

        override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
            glSurfaceViewWidth = width
            glSurfaceViewHeight = height
        }

        override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
            renderer = ScreenRenderer()
            renderer.init()
            val bitmap = FileUtil.decodeBitmapFromAssets("image_0.jpg")
            val texture = GLUtil.createTexture()
            GLUtil.loadBitmap2Texture(texture, bitmap)
            input = Texture(texture, bitmap.width, bitmap.height, false)
        }

    }

}
