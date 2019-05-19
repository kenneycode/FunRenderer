package kenneycode.funrenderer.common

import kenneycode.funrenderer.io.FrameBuffer
import java.util.*

/**
 *
 *      Coded by kenney
 *
 *      http://www.github.com/kenneycode
 *
 **/

object FrameBufferCache {

    private val cache = mutableMapOf<Size, LinkedList<FrameBuffer>>()

    fun obtainFrameBuffer(width: Int = 0, height: Int = 0) : FrameBuffer {
        val size = Size(width, height)
        if (!cache.containsKey(size)) {
            cache[size] = LinkedList()
        }
        val frameBufferQueue = cache[size]!!
        if (frameBufferQueue.isEmpty()) {
            frameBufferQueue.add(FrameBuffer())
        } else {
            frameBufferQueue.first.addRef()
        }
        return frameBufferQueue.pop()
    }

    fun releaseFrameBuffer(frameBuffer: FrameBuffer) {
        cache[Size(frameBuffer.width, frameBuffer.height)]!!.add(frameBuffer)
    }

}