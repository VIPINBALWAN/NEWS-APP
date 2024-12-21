package com.example.reycleviewitemclick

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val a=intent.getStringExtra("image")
        val b=intent.getStringExtra("title")
        val c=intent.getStringExtra("desc")

        val image=findViewById<ImageView>(R.id.topimage)
        val title=findViewById<TextView>(R.id.heading)
        val desc=findViewById<TextView>(R.id.desc)

        Glide.with(this)
            .load(a)
            .placeholder(R.drawable.baseline_image_24)
            .into(image)
        title.text=b
        desc.text=c

    }
}