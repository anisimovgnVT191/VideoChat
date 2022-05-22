package com.example.android.videochat.data.rtc

import com.example.android.videochat.data.models.OfferModel
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

interface RtcClient {
    fun createSdpOffer(): BehaviorSubject<OfferModel>

    fun createSdpAnswer(): BehaviorSubject<OfferModel>

    fun onSdpOfferReceived(offerModel: OfferModel)
}