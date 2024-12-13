package com.example.mediabrowsertest

import android.content.ComponentName
import android.content.Context
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaControllerCompat
import android.util.Log

class TestServiceHelper(
    private val context: Context
) {
    private val TAG = TestServiceHelper::class.java.name
    private val browser: MediaBrowserCompat = MediaBrowserCompat(
        context,
        ComponentName(context, TestService::class.java),
        object : MediaBrowserCompat.ConnectionCallback() {
            override fun onConnected() {
                super.onConnected()
                Log.e(TAG, "[Browser] onConnected")

                initializeMediaController()
            }

            override fun onConnectionSuspended() {
                super.onConnectionSuspended()
                Log.e(TAG, "[Browser] onConnectionSuspended")
            }

            override fun onConnectionFailed() {
                super.onConnectionFailed()
                Log.e(TAG, "[Browser] onConnectionFailed")
            }
        },
        null
    )

    private lateinit var controller: MediaControllerCompat
    private fun initializeMediaController() {
        controller = MediaControllerCompat(context, browser.sessionToken).apply {
        }
    }

    fun connect() {
        browser.connect()
    }

    fun play() {
        controller.transportControls.play()
    }

    fun pause() {
        controller.transportControls.pause()
    }

    fun disconnect() {
        browser.disconnect()
    }
}