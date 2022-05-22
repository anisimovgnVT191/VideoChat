package com.example.android.videochat.data.rtc

import com.example.android.videochat.data.models.IceCandidateModel
import com.example.android.videochat.data.models.OfferModel
import com.example.android.videochat.data.rtc.utils.PeerConnectionObserver
import com.example.android.videochat.data.rtc.utils.SdpObserver
import com.example.android.videochat.presentation.models.SessionOfferType
import io.reactivex.subjects.BehaviorSubject
import org.webrtc.*
import org.webrtc.SessionDescription.Type.OFFER
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RtcClientImpl @Inject constructor(
    private val iceServersSet: Set<PeerConnection.IceServer>
) : RtcClient {
    val iceCandidateSubject = BehaviorSubject.create<IceCandidateModel>()
    private val peerConnectionObserver = object : PeerConnectionObserver() {
        override fun onIceCandidate(p0: IceCandidate?) {
            p0?.let { iceCandidate ->
                iceCandidateSubject.onNext(iceCandidate.toModel())
            }
        }
    }

    val sdpOfferSubject = BehaviorSubject.create<OfferModel>()
    private val sdpObserver = object : SdpObserver() {
        override fun onCreateSuccess(p0: SessionDescription?) {
            p0?.let { sessionDescription ->
                peerConnection.setLocalDescription(SdpObserver(), sessionDescription)
                sdpOfferSubject.onNext(sessionDescription.toOfferModel())
            }
        }
    }

    private val rootEglBase: EglBase = EglBase.create()
    private val peerConnectionFactory: PeerConnectionFactory by lazy { buildPeerConnectionFactory() }
    private val peerConnection: PeerConnection by lazy { buildPeerConnection() }

    private val mediaConstraints = MediaConstraints().apply {
        mandatory.add(MediaConstraints.KeyValuePair(OFFER_TO_RECEIVE_AUDIO, "true"))
        mandatory.add(MediaConstraints.KeyValuePair(OFFER_TO_RECEIVE_VIDEO, "true"))
    }

    override fun createSdpOffer(): BehaviorSubject<OfferModel> {
        peerConnection.createOffer(sdpObserver, mediaConstraints)
        return sdpOfferSubject
    }

    override fun createSdpAnswer(): BehaviorSubject<OfferModel> {
        peerConnection.createAnswer(sdpObserver, MediaConstraints())
        return sdpOfferSubject
    }

    override fun onSdpOfferReceived(offerModel: OfferModel) {
        peerConnection.setRemoteDescription(sdpObserver, offerModel.toSessionDescription())
    }

    private fun buildPeerConnectionFactory(): PeerConnectionFactory {
        return PeerConnectionFactory
            .builder()
            .setVideoDecoderFactory(DefaultVideoDecoderFactory(rootEglBase.eglBaseContext))
            .setVideoEncoderFactory(
                DefaultVideoEncoderFactory(
                    rootEglBase.eglBaseContext,
                    true,
                    true
                )
            )
            .createPeerConnectionFactory()
    }

    private fun buildPeerConnection(): PeerConnection {
        return peerConnectionFactory.createPeerConnection(
            iceServersSet.toList(),
            peerConnectionObserver
        )!!
    }

    private fun IceCandidate.toModel() = with(this) {
        IceCandidateModel(adapterType, sdp, sdpMLineIndex, sdpMid, serverUrl)
    }

    private fun SessionDescription.toOfferModel() = with(this) {
        OfferModel(
            sdp = description,
            type = when (type) {
                OFFER -> SessionOfferType.OFFER
                else -> SessionOfferType.ANSWER
            }
        )
    }

    private fun OfferModel.toSessionDescription() = with(this) {
        SessionDescription(
            when (type) {
                SessionOfferType.OFFER -> SessionDescription.Type.OFFER
                else -> SessionDescription.Type.ANSWER
            }, sdp
        )
    }

    companion object {
        private const val OFFER_TO_RECEIVE_VIDEO = "OfferToReceiveVideo"
        private const val OFFER_TO_RECEIVE_AUDIO = "OfferToReceiveAudio"
    }
}