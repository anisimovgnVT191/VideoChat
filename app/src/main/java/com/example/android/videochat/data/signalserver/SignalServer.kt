package com.example.android.videochat.data.signalserver

import com.example.android.videochat.data.models.IceCandidateModel
import com.example.android.videochat.data.models.OfferModel
import com.example.android.videochat.presentation.models.SessionOfferType
import com.example.android.videochat.presentation.models.UserType
import io.reactivex.subjects.BehaviorSubject
import org.webrtc.*

interface SignalServer {
    fun sendCallOffer(
        callId: String,
        sdp: String,
        type: SessionOfferType
    ): BehaviorSubject<Unit>

    fun getCallOffer(
        callId: String,
        type: SessionOfferType
    ): BehaviorSubject<OfferModel>

    fun sendIceCandidate(
        callId: String,
        userType: UserType,
        iceCandidate: IceCandidateModel
    ): BehaviorSubject<Unit>

    fun getIceCandidate(
        callId: String,
        userType: UserType
    ): BehaviorSubject<IceCandidateModel>
}
