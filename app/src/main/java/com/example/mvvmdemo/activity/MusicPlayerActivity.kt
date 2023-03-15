package com.example.mvvmdemo.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmdemo.R
import com.example.mvvmdemo.adapter.MusicQueueAdapter
import com.example.mvvmdemo.dataclass.MusicDataClass
import com.example.mvvmdemo.viewmodel.MusicViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_music_player.*
import kotlinx.android.synthetic.main.bootmsheet_music_queue.view.*
class MusicPlayerActivity : AppCompatActivity() {

    private var isPlaying: Boolean = false
    private var isPause: Boolean = false

    private lateinit var viewModel: MusicViewModel

    private var musicQueueList = arrayListOf<MusicDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        viewModel = ViewModelProvider(this)[MusicViewModel::class.java]

        viewModel._musicTiming.observe(this) {
            music_total_time.text = it.toString()
        }

        //  PLAY-PAUSE BUTTON
        music_play_pause.setOnClickListener {
            if (!isPlaying) {
                viewModel.playMusic(this)
                viewModel.initializeSeekBar(music_seekbar, music_current_time)
                isPlaying = true
                music_play_pause.setImageResource(R.drawable.baseline_pause_24)
            }
            else if (isPlaying) {
                isPause = true
                viewModel.pauseMusic()
                music_next.isEnabled = true
                isPlaying = false
                music_play_pause.setImageResource(R.drawable.baseline_play_arrow_24)
            }
            else if (!isPlaying && isPause) {
                isPlaying = true
                viewModel.playMusic(this)
                music_next.isEnabled = true
                isPause = false
                music_play_pause.setImageResource(R.drawable.baseline_pause_24)
            }
        }

        //  STOP BUTTON
        music_next.setOnClickListener {

        }

        //  QUEUE BUTTON
        music_queue.setOnClickListener {
            getMusicQueue()
        }
    }

    private fun getMusicQueue() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bootmsheet_music_queue, null)
        bottomSheetDialog.setCancelable(true)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()

        musicQueueList = ArrayList<MusicDataClass>()
        musicQueueList.apply {
            add(MusicDataClass("Song 1"))
            add(MusicDataClass("Song 2"))
            add(MusicDataClass("Song 3"))
            add(MusicDataClass("Song 4"))
            add(MusicDataClass("Song 5"))
        }

        view.recycler_music_queue.apply {
            layoutManager = LinearLayoutManager(this@MusicPlayerActivity, LinearLayoutManager.VERTICAL, false)
            adapter =  MusicQueueAdapter(this@MusicPlayerActivity, musicQueueList)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        viewModel._musicQueueTitle.observe(this) {
            Log.d("Files", it.toString())
        }
    }

}