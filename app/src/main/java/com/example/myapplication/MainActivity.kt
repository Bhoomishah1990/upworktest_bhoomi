package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.VideoAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewmodels.VideoViewModel
//Upside Down Cake
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: VideoViewModel
    private lateinit var videoadapter: VideoAdapter
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[VideoViewModel::class.java]
        viewModel.getPopularMovies()
        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            videoadapter.setVideoList(movieList)

        })
    }

    private fun prepareRecyclerView() {
        videoadapter = VideoAdapter(this)
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = videoadapter
        }

    }
}
