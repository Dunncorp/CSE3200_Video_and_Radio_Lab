package com.example.k2023_04_23_boundservicetextview.classes

import android.content.Context
import android.media.MediaPlayer

class AudioManager(context: Context) {
    private val mediaPlayer = MediaPlayer()

    init {
        mediaPlayer.setOnPreparedListener { mediaPlayer.start() }
    }

    fun playRadioStream(url: String) {
        mediaPlayer.apply {
            reset()
            setDataSource(url)
            prepareAsync()
        }
    }

    fun stop() {
        mediaPlayer.stop()
        mediaPlayer.reset()
    }

    fun release() {
        mediaPlayer.release()
    }
}