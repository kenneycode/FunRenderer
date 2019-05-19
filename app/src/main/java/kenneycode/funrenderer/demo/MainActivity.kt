package kenneycode.funrenderer.demo

import android.opengl.GLSurfaceView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kenneycode.funrenderer.common.Renderer
import kenneycode.funrenderer.common.ScreenRenderer
import kenneycode.funrenderer.io.Input
import kenneycode.funrenderer.io.Texture
import kenneycode.funrenderer.uitl.FileUtil
import kenneycode.funrenderer.uitl.GLUtil
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FileUtil.context = applicationContext
        setContentView(R.layout.activity_main)
        val glSurfaceView = findViewById<GLSurfaceView>(R.id.glSurfaceView)
        glSurfaceView.setEGLContextClientVersion(3)
        glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 0, 0)
        glSurfaceView.setRenderer(MyRenderer())

    }

    inner class MyRenderer : GLSurfaceView.Renderer {

        private lateinit var renderer: Renderer
        private lateinit var input: Input

        private var glSurfaceViewWidth = 0
        private var glSurfaceViewHeight = 0

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
