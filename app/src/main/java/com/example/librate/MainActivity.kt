package com.example.librate

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.librate_gaker.DialogRate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_showRate).setOnClickListener {
            DialogRate.newInstance(this, "AR Draw",R.drawable.logo,"khanhjos20@gmail.com").show(supportFragmentManager,"show_rate")
        }

    }
}