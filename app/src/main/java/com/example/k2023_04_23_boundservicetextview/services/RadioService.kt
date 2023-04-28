package com.example.k2023_04_23_boundservicetextview.services

import android.R
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.browse.MediaBrowser.MediaItem
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util

import java.util.Random


class RadioService : Service() {
    private val classicRockRadioStream: String = "https://0n-classicrock.radionetz.de/0n-classicrock.mp3"
    private val uconnRadioStream: String = "http://stream.whus.org:8000/whusfm"

    private lateinit var myMediaController: MediaController

    private val binder = LocalBinder()
    private var selectedRadioText: String = "Start"

    private var mediaPlayer: ExoPlayer? = null

    private var isRadioPrepared: Boolean = false

    private val mGenerator = Random()
    /*
/*
    private fun initializePlayer() {
        val bandwidthMeter = DefaultBandwidthMeter.Builder(context).build()
        val dataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, "RadioService"), bandwidthMeter)

        val trackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(trackSelectionFactory)
        val loadControl = DefaultLoadControl()

        val player = ExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .setLoadControl(loadControl)
            .build()


    }
    */


    val randomNumber: Int
        get() = mGenerator.nextInt(100)

    fun updateRadioText(newText: String) {
        selectedRadioText = newText
    }

    fun prepare() {

    }

    fun pauseVideoView(videoView: VideoView) {

    }

 */

    inner class LocalBinder : Binder() {
        fun getService(): RadioService = this@RadioService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}