package com.example.android.videochat

import android.app.Application
import android.util.Log
import com.example.android.videochat.di.AppComponentHolder
import com.example.android.videochat.di.DaggerAppComponent
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class VideoChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppComponentHolder.set {
            DaggerAppComponent.factory().create(this)
        }
    }
}