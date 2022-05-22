package com.example.android.videochat.data.rtc.utils

import org.webrtc.SdpObserver
import org.webrtc.SessionDescription

open class SdpObserver : SdpObserver {
    override fun onCreateSuccess(p0: SessionDescription?) = Unit

    override fun onSetSuccess() = Unit

    override fun onCreateFailure(p0: String?) = Unit

    override fun onSetFailure(p0: String?) = Unit
}