package com.example.android.videochat.data.rtc

import com.example.android.videochat.data.models.OfferModel
import io.reactivex.subjects.PublishSubject

interface RtcClient {
    fun createSdpOffer(): PublishSubject<OfferModel>

    fun createSdpAnswer(): PublishSubject<OfferModel>

    fun onSdpOfferReceived(offerModel: OfferModel)
}