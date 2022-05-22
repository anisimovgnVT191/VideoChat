package com.example.android.videochat.domain

import com.example.android.videochat.presentation.models.UserType
import io.reactivex.Observable
import io.reactivex.Single

interface CallRepository {
    fun startCall(callId: String): Single<Unit>

    fun joinCall(callId: String): Single<Unit>

    fun getCalleeResponse(callId: String): Single<Unit>

    fun startIceCandidatesExchange(callId: String, userType: UserType): Observable<Unit>
}