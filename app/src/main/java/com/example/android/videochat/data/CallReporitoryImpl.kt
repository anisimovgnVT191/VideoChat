package com.example.android.videochat.data

import com.example.android.videochat.data.rtc.RtcClient
import com.example.android.videochat.data.signalserver.SignalServer
import com.example.android.videochat.domain.CallRepository
import com.example.android.videochat.presentation.models.SessionOfferType
import com.example.android.videochat.presentation.models.UserType
import io.reactivex.Observable
import javax.inject.Inject

class CallRepositoryImpl @Inject constructor(
    private val signalServer: SignalServer,
    private val rtcClient: RtcClient
) : CallRepository {
    override fun startCall(callId: String) = rtcClient.createSdpOffer()
        .switchMap { offer ->
            signalServer.sendCallOffer(callId, offer.sdp, offer.type)
        }.take(1).singleOrError()


    override fun joinCall(callId: String) =
        signalServer.getCallOffer(callId, SessionOfferType.OFFER)
            .flatMap { offer ->
                rtcClient.onSdpOfferReceived(offer)
                rtcClient.createSdpAnswer()
            }
            .switchMap { offer ->
                signalServer.sendCallOffer(callId, offer.sdp, offer.type)
            }.take(1).singleOrError()

    override fun getCalleeResponse(callId: String) =
        signalServer.getCallOffer(callId, SessionOfferType.ANSWER)
            .take(1)
            .flatMap { offer ->
                Observable.just(rtcClient.onSdpOfferReceived(offer))
            }.singleOrError()

    override fun startIceCandidatesExchange(callId: String, userType: UserType): Observable<Unit> {
        return Observable.merge(
            addIceCandidateToDb(callId, userType),
            getIceCandidateToDb(callId, userType)
        )
    }

    private fun addIceCandidateToDb(callId: String, userType: UserType) =
        rtcClient.subscribeToIceCandidates()
            .flatMap { iceCandidate ->
                signalServer.sendIceCandidate(callId, userType, iceCandidate)
            }

    private fun getIceCandidateToDb(callId: String, userType: UserType) =
        signalServer.getIceCandidate(callId, userType)
            .flatMap { iceCandidate ->
                Observable.just(rtcClient.addIceCandidate(iceCandidate))
            }
}