package com.example.android.videochat.data.rtc.utils

import org.webrtc.*

open class PeerConnectionObserver : PeerConnection.Observer {
    override fun onSignalingChange(p0: PeerConnection.SignalingState?) = Unit

    override fun onIceConnectionChange(p0: PeerConnection.IceConnectionState?) = Unit

    override fun onIceConnectionReceivingChange(p0: Boolean) = Unit

    override fun onIceGatheringChange(p0: PeerConnection.IceGatheringState?) = Unit

    override fun onIceCandidate(p0: IceCandidate?) = Unit

    override fun onIceCandidatesRemoved(p0: Array<out IceCandidate>?) = Unit

    override fun onAddStream(p0: MediaStream?) = Unit

    override fun onRemoveStream(p0: MediaStream?) = Unit

    override fun onDataChannel(p0: DataChannel?) = Unit

    override fun onRenegotiationNeeded() = Unit

    override fun onAddTrack(p0: RtpReceiver?, p1: Array<out MediaStream>?) = Unit
}