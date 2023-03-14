package com.example.mvvmdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemo.R
import com.example.mvvmdemo.viewmodel.MusicViewModel
import kotlinx.android.synthetic.main.activity_music_player.*
import java.util.concurrent.TimeUnit

class MusicPlayerActivity : AppCompatActivity() {

    private var isPlaying: Boolean = false
    private var isPause: Boolean = false
    private var isStop: Boolean = false

    private lateinit var viewModel: MusicViewModel
    lateinit var runnable: Runnable
    var handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        viewModel = ViewModelProvider(this)[MusicViewModel::class.java]
        viewModel._musicTiming.observe(this) {
            music_total_time.text = it.toString()
        }

        music_play_pause.setOnClickListener {
            if (!isPlaying) {
                initializeSeekBar()
                isPlaying = true
                isStop = false
                music_stop.isEnabled = true
                viewModel.playMusic(this)
                music_play_pause.setImageResource(R.drawable.baseline_pause_24)
            }
            else if (isPlaying) {
                isPause = true
                viewModel.pauseMusic()
                isStop = false
                music_stop.isEnabled = true
                isPlaying = false
                music_play_pause.setImageResource(R.drawable.baseline_play_arrow_24)
            }
            else if (!isPlaying && isPause) {
                isPlaying = true
                viewModel.resumeMusic()
                isStop = false
                music_stop.isEnabled = true
                isPause = false
                music_play_pause.setImageResource(R.drawable.baseline_pause_24)
            }
            else if (!isPlaying && isStop) {
                isPlaying = true
                viewModel.playMusic(this)
                isPause = false
                isStop = false
                music_play_pause.setImageResource(R.drawable.baseline_pause_24)
            }
        }

        music_stop.setOnClickListener {
            isStop = true
            isPlaying = false
            isPause = false
            viewModel.stopMusic()
            music_stop.isEnabled = false
            music_play_pause.setImageResource(R.drawable.baseline_play_arrow_24)
        }

        music_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    viewModel.getMediaPlayer().seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                viewModel.getMediaPlayer().seekTo(seekBar?.progress ?: 0)
                viewModel.getMediaPlayer().start()
            }
        })

    }

    private fun initializeSeekBar() {
        music_seekbar.max = viewModel.getMediaPlayer().duration / 1000

        runnable = Runnable {
            music_seekbar.progress = viewModel.getMediaPlayer().currentPosition / 1000

            val currentTime = String.format("%d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(viewModel.getMediaPlayer().currentPosition.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(viewModel.getMediaPlayer().currentPosition.toLong()) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(viewModel.getMediaPlayer().currentPosition.toLong())))

            music_current_time.text = currentTime
            handler.postDelayed(runnable, 100)
        }
        handler.postDelayed(runnable, 100)
    }

}

