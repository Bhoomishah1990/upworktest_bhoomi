package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.adapter.ImagesAdapter
import com.example.myapplication.adapter.VideoAdapter
import com.example.myapplication.databinding.ActivityDetailLayoutBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.models.Result
import com.example.myapplication.models.getSerializable
import com.example.myapplication.viewmodels.VideoViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailLayoutBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.getSerializable("Obj", Result::class.java)
        binding.tvTitle.text = data.title
        binding.tvCategory.text = getString(R.string.category) + data.category
        var img = data.image_thumb?.replace("[", "")?.replace("]", "")?.replace("\"", "")
            ?.replace("\'", "")?.replace("https:", "")?.split(",")



        binding.viewpagerImages.apply {
            layoutManager = LinearLayoutManager(
                applicationContext, LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = img?.let { ImagesAdapter(it,this@DetailActivity) }
        }
        binding.btnPlayVideo.setOnClickListener {
            val i: Intent = Intent(this, VideoActivity::class.java)
            i.putExtra("Obj", data)
            startActivity(i)
        }
    }
}