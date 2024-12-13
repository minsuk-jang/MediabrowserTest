package com.example.mediabrowsertest

import android.content.ComponentName
import android.media.browse.MediaBrowser
import android.os.Bundle
import android.service.media.MediaBrowserService
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.media.MediaBrowserServiceCompat

class TestService : MediaBrowserServiceCompat() {
    private var session: MediaSessionCompat? = null
    private val TAG = TestService::class.java.name
    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "[Service] onCreate")
        getMediaSession()
    }

    private fun getMediaSession() {
        session = MediaSessionCompat(
            this,
            "TestSession",
            ComponentName(this, MediaBrowserService::class.java.name),
            null
        ).apply {
            setCallback(object : MediaSessionCompat.Callback() {
                override fun onPlay() {
                    super.onPlay()
                    Log.e(TAG, "[Session] onPlay")
                }

                override fun onPause() {
                    super.onPause()
                    Log.e(TAG, "[Session] onPause")
                }
            })

            isActive = true
        }

        sessionToken = session?.sessionToken
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        return BrowserRoot("root", null)
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        TODO("Not yet implemented")
    }
}