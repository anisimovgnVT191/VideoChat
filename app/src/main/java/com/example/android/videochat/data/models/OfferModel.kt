package com.example.android.videochat.data.models

import com.example.android.videochat.presentation.models.SessionOfferType

data class OfferModel(
    val sdp: String,
    val type: SessionOfferType
)