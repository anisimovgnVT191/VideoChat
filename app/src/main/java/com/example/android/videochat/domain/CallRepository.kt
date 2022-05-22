package com.example.android.videochat.domain

import io.reactivex.Single

interface CallRepository {
    fun startCall(callId: String): Single<Unit>

    fun joinCall(callId: String): Single<Unit>

    fun getCalleeResponse(callId: String): Single<Unit>
}