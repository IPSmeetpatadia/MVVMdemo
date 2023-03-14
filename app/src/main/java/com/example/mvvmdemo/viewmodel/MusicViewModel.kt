package com.example.mvvmdemo.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.R
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

class MusicViewModel : ViewModel() {

    var _musicTiming = MutableLiveData<String>()
    private var mediaPlayer: MediaPlayer = MediaPlayer()

    fun playMusic(context: Context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.pyaar_hota_kayi_baar_hai)
        mediaPlayer.start()
        _musicTiming.value = convertToMinute(mediaPlayer.duration)
    }

    fun resumeMusic() {
        mediaPlayer.seekTo(mediaPlayer.currentPosition)
        mediaPlayer.start()
    }

    fun pauseMusic() {
        mediaPlayer.pause()
    }

    fun stopMusic() {
        mediaPlayer.stop()
        mediaPlayer.reset()
    }

    private fun convertToMinute(duration: Int): String {
        return String.format("%d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(duration.toLong()),
            TimeUnit.MILLISECONDS.toSeconds(duration.toLong()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration.toLong()))
        )
    }

    fun getMediaPlayer(): MediaPlayer {
        return mediaPlayer
    }

}