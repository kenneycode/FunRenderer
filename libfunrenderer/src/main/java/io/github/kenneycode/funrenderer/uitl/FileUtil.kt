package io.github.kenneycode.funrenderer.uitl

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log

class FileUtil {

    companion object {

        lateinit var context : Context

        fun decodeBitmapFromAssets(filename : String) : Bitmap {
            val options = BitmapFactory.Options()
            options.inSampleSize = 1
            val bitmap = BitmapFactory.decodeStream(context.assets.open(filename))
            if (bitmap == null) {
                Log.e("debug", "bitmap decode fail, path = $filename")
            }
            return bitmap
        }

    }

}