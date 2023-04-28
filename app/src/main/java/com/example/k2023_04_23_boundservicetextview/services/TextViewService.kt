package com.example.k2023_04_23_boundservicetextview.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.view.View
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import java.util.*

class TextViewService  : Service() {
    private val bunnyStream: String = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    private val sintelStream: String = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"
    private val elephantsDream: String = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
    private val videoStream: String = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    private lateinit var myMediaController: MediaController

    private val binder = LocalBinder()
    private var selectedVideoText: String = "Start"
    private val mediaPlayer: MediaPlayer? = null

    private var isVideoPrepared: Boolean = false

    private val mGenerator = Random()

    fun setupMediaPlayer(videoView: VideoView) {
        videoView.setVideoURI(Uri.parse(videoStream))
        myMediaController = MediaController(this)
        myMediaController.setAnchorView(videoView)
        myMediaController.setMediaPlayer(videoView)
    }

    val randomNumber: Int
        get() = mGenerator.nextInt(100)

    fun updateVideoText(newText: String) {
        selectedVideoText = newText
    }
    fun startVideoView(videoView: VideoView) {
        if (!videoView.isActivated) {
            videoView.start()
        } else {
            if (!videoView.isPlaying) {
                videoView.resume()
                Toast.makeText(this, "Video Started", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Video Already Playing", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun pauseVideoView(videoView: VideoView) {
        if (videoView.isPlaying) {
            videoView.pause()
            Toast.makeText(this, "Video Paused", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Video Already Paused", Toast.LENGTH_SHORT).show()
        }
    }

    inner class LocalBinder : Binder() {
        fun getService(): TextViewService = this@TextViewService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}