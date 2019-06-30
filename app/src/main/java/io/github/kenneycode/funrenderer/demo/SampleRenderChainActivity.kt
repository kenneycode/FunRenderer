package io.github.kenneycode.funrenderer.demo

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import io.github.kenneycode.funrenderer.common.Keys
import io.github.kenneycode.funrenderer.common.RenderChain
import io.github.kenneycode.funrenderer.io.Input
import io.github.kenneycode.funrenderer.io.Texture
import io.github.kenneycode.funrenderer.renderer.FlipRenderer
import io.github.kenneycode.funrenderer.renderer.ScaleRenderer
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

class SampleRenderChainActivity : Activity() {

    private lateinit var renderChain: RenderChain
    private lateinit var input: Input

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_render_chain)
        title = intent.getStringExtra(Constants.KEY_SAMPLE_NAME)
        val glSurfaceView = findViewById<GLSurfaceView>(R.id.glSurfaceView)
        glSurfaceView.setEGLContextClientVersion(3)
        glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 0, 0)
        glSurfaceView.setRenderer(MyRenderer())
    }

    override fun onDestroy() {
        super.onDestroy()
        input.release()
        renderChain.release()
    }

    inner class MyRenderer : GLSurfaceView.Renderer {

        private var glSurfaceViewWidth = 0
        private var glSurfaceViewHeight = 0

        override fun onDrawFrame(gl: GL10?) {
            val data = mutableMapOf<String, Any>()
            data[Keys.SURFACE_WIDTH] = glSurfaceViewWidth
            data[Keys.SURFACE_HEIGHT] = glSurfaceViewHeight
            renderChain.render(input, data)
        }

        override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
            glSurfaceViewWidth = width
            glSurfaceViewHeight = height
        }

        override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
            val flipRenderer = FlipRenderer()
            val scaleRenderer = ScaleRenderer()
            val screenRenderer = ScreenRenderer()
            scaleRenderer.setScaledSize(100, 100)
            renderChain = RenderChain.create()
                    .addRenderer(flipRenderer)
                    .addRenderer(scaleRenderer)
                    .addRenderer(screenRenderer)
            renderChain.init()
            val bitmap = FileUtil.decodeBitmapFromAssets("image_0.jpg")
            val texture = GLUtil.createTexture()
            GLUtil.loadBitmap2Texture(texture, bitmap)
            input = Texture(texture, bitmap.width, bitmap.height, false)
        }

    }

}
