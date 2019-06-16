package io.github.kenneycode.funrenderer.common

data class Size(var width: Int = 0, var height: Int = 0) {

    fun isValid(): Boolean {
        return width > 0 && height > 0
    }

}