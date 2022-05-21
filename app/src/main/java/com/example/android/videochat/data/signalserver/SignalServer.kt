package com.example.android.videochat.data.signalserver

import org.webrtc.*

interface SignalServer {
    fun sendCallOffer(
        callId: String,
        sdp: String,
        type: SessionDescription.Type
    )

    fun getCallOffer(
        callId: String
    )
}

val test = PeerConnectionFactory.builder().createPeerConnectionFactory().createPeerConnection(listOf(), object : PeerConnection.Observer {
    override fun onSignalingChange(p0: PeerConnection.SignalingState?) {
        TODO("Not yet implemented")
    }

    override fun onIceConnectionChange(p0: PeerConnection.IceConnectionState?) {
        TODO("Not yet implemented")
    }

    override fun onIceConnectionReceivingChange(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onIceGatheringChange(p0: PeerConnection.IceGatheringState?) {
        TODO("Not yet implemented")
    }

    override fun onIceCandidate(p0: IceCandidate?) {
        TODO("Not yet implemented")
    }

    override fun onIceCandidatesRemoved(p0: Array<out IceCandidate>?) {
        TODO("Not yet implemented")
    }

    override fun onAddStream(p0: MediaStream?) {
        TODO("Not yet implemented")
    }

    override fun onRemoveStream(p0: MediaStream?) {
        TODO("Not yet implemented")
    }

    override fun onDataChannel(p0: DataChannel?) {
        TODO("Not yet implemented")
    }

    override fun onRenegotiationNeeded() {
        TODO("Not yet implemented")
    }

    override fun onAddTrack(p0: RtpReceiver?, p1: Array<out MediaStream>?) {
        TODO("Not yet implemented")
    }

})?.createOffer(object : SdpObserver {
    override fun onCreateSuccess(p0: SessionDescription?) {
        p0?.type
    }

    override fun onSetSuccess() {
        TODO("Not yet implemented")
    }

    override fun onCreateFailure(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onSetFailure(p0: String?) {
        TODO("Not yet implemented")
    }

}, MediaConstraints())