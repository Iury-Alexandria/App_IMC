package com.app.iury.appimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.iury.appimc.databinding.ActivityImcBinding
import com.app.iury.appimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventClicks()
    }// final onCreate


    override fun onClick(view: View) {
        if (view.id == R.id.view_ctn_01 ||
            view.id == R.id.view_ctn_02 ||
            view.id == R.id.view_ctn_03
        ) {
            startActivity(Intent(this, ActivitImc::class.java))
        }
    }

    private fun eventClicks() {
        binding.viewCtn01.setOnClickListener(this)
        binding.viewCtn02.setOnClickListener(this)
        binding.viewCtn03.setOnClickListener(this)

    }


}

