package com.example.android.videochat.data

import com.example.android.videochat.data.rtc.RtcClient
import com.example.android.videochat.data.signalserver.SignalServer
import com.example.android.videochat.presentation.models.SessionOfferType
import javax.inject.Inject

class CallRepositoryImpl @Inject constructor(
    private val signalServer: SignalServer,
    private val rtcClient: RtcClient
) {

    fun startCall(callId: String) = rtcClient.createSdpOffer()
        .switchMap { offer ->
            signalServer.sendCallOffer(callId, offer.sdp, offer.type)
        }.singleOrError()


    fun joinCall(callId: String) = signalServer.getCallOffer(callId, SessionOfferType.OFFER)
        .flatMap { offer ->
            rtcClient.onSdpOfferReceived(offer)
            rtcClient.createSdpAnswer()
        }
        .map { offer ->
            signalServer.sendCallOffer(callId, offer.sdp, offer.type)
        }.singleOrError()

}