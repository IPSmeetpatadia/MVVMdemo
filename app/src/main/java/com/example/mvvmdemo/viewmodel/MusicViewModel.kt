package com.example.mvvmdemo.viewmodel

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.R
import java.util.concurrent.TimeUnit

class MusicViewModel : ViewModel() {

    var _musicTiming = MutableLiveData<String>()
    var _musicQueueTitle = MutableLiveData<java.util.ArrayList<String>>()
    private var mediaPlayer: MediaPlayer = MediaPlayer()

    private var pause: Boolean = false
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()

    fun playMusic(context: Context) {
        if (pause) {
            mediaPlayer.seekTo(mediaPlayer.currentPosition)
            mediaPlayer.start()
        } else {
            mediaPlayer = MediaPlayer.create(context, R.raw.pyaar_hota_kayi_baar_hai)
            mediaPlayer.start()
            _musicTiming.value = convertToMinute(mediaPlayer.duration)
        }
    }

    fun pauseMusic() {
        if(mediaPlayer != null){
        pause = true
        mediaPlayer.pause()
        }
    }

    private fun convertToMinute(duration: Int): String {
        return String.format(
            "%d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(duration.toLong()),
            TimeUnit.MILLISECONDS.toSeconds(duration.toLong()) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    duration.toLong()
                )
            )
        )
    }

    fun getMediaPlayer(): MediaPlayer {
        return mediaPlayer
    }

    fun initializeSeekBar(music_seekbar: SeekBar, music_current_time: TextView) {
        music_seekbar.max = mediaPlayer.duration

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    music_seekbar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)

                    val currentTime = String.format("%d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.currentPosition.toLong()),
                        TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.currentPosition.toLong()) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.currentPosition.toLong()))
                    )
                    music_current_time.text = currentTime
                }
                catch (e: java.lang.Exception) {
                    music_seekbar.progress = 0
                }
            }
        }, 100)

        //  SEEKBAR LISTENER
        music_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) mediaPlayer.seekTo(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

}