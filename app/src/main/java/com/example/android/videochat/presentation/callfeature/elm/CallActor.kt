package com.example.android.videochat.presentation.callfeature.elm

import com.example.android.videochat.presentation.callfeature.elm.models.CallCommand
import com.example.android.videochat.presentation.callfeature.elm.models.CallEvent
import io.reactivex.Observable
import vivid.money.elmslie.core.Actor
import javax.inject.Inject

class CallActor @Inject constructor(): Actor<CallCommand, CallEvent> {
    override fun execute(command: CallCommand): Observable<CallEvent> {
        TODO("Not yet implemented")
    }
}