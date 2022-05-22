package com.example.android.videochat.data.models

import org.webrtc.PeerConnection

data class IceCandidateModel(
    val adapterType: PeerConnection.AdapterType,
    val sdp: String,
    val sdpMLineIndex: Int,
    val sdpMid: String,
    val serverUrl: String
)
