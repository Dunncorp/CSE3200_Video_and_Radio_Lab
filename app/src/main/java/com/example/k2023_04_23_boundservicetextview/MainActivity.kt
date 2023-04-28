package com.example.k2023_04_23_boundservicetextview

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.k2023_04_23_boundservicetextview.services.TextViewService

class MainActivity : AppCompatActivity() {
    private lateinit var mService: TextViewService
    private var mBound: Boolean = false
    private lateinit var videoPlayButton: Button
    private lateinit var videoPauseButton: Button
    private lateinit var radioPlayButton: Button
    private lateinit var radioPauseButton: Button
    private lateinit var selectedVideoTextView: TextView
    private lateinit var videoView: VideoView

    private val mediaPlayer = MediaPlayer()
    private val uconnRadioStream: String = "http://stream.whus.org:8000/whusfm"

    private fun initializeRadio() {
        mediaPlayer.setOnPreparedListener{ mediaPlayer.start() }
    }

    private fun playRadioStream(uri: String) {
        mediaPlayer.apply {
            reset()
            setDataSource(uri)
            prepareAsync()
        }
    }
    fun release() {
        mediaPlayer.release()
    }


    /** Defines callbacks for service binding, passed to bindService().  */
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as TextViewService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }
    /*

    private var viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                viewBinding.playerView = exoPlayer
                val mediaItem = MediaItem.fromUri(uconnRadioStream)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem, playbackPosition)
                exoPlayer.prepare()
            }

    }

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        videoPlayButton = findViewById(R.id.play_video_button)
        videoPauseButton = findViewById(R.id.pause_video_button)
        radioPlayButton = findViewById(R.id.play_radio_button)
        radioPauseButton = findViewById(R.id.pause_radio_button)
        selectedVideoTextView = findViewById(R.id.selectedVideoTextView)
        videoView = findViewById(R.id.videoView)

        videoPlayButton.setOnClickListener {
            mService.setupMediaPlayer(videoView)
            mService.startVideoView(videoView)
        }
        videoPauseButton.setOnClickListener {
            mService.pauseVideoView(videoView)
        }
        radioPlayButton.setOnClickListener {
            initializeRadio()
            playRadioStream(uconnRadioStream)
            Toast.makeText(this, "Radio Started", Toast.LENGTH_SHORT).show()
        }
        radioPauseButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            Toast.makeText(this, "Radio Paused", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        /*
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }

         */
        Intent(this, TextViewService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }
    /*

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, viewBinding.videoView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUi()
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer()
        }
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }

     */
}