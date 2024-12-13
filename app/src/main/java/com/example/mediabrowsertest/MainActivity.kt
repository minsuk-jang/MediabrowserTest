package com.example.mediabrowsertest

import android.content.ComponentName
import android.media.browse.MediaBrowser
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaControllerCompat
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.mediabrowsertest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val helper1: TestServiceHelper by lazy {
        TestServiceHelper(this)
    }

    private val helper2: TestServiceHelper by lazy {
        TestServiceHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        bindConnectButton()
        bindPlayButton()
        bindPauseButton()
    }

    private fun bindConnectButton() {
        binding.buttonConnect1.setOnClickListener {
            helper1.connect()
        }

        binding.buttonConnect2.setOnClickListener {
            helper2.connect()
        }
    }

    private fun bindPlayButton() {
        binding.buttonPlay1.setOnClickListener {
            helper1.play()
        }

        binding.buttonPlay2.setOnClickListener {
            helper2.play()
        }
    }

    private fun bindPauseButton() {
        binding.buttonPause1.setOnClickListener {
            helper1.pause()
        }

        binding.buttonPause2.setOnClickListener {
            helper2.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        helper1.disconnect()
        helper2.disconnect()
    }
}