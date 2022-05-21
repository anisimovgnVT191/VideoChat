package com.example.android.videochat.presentation.startcallfeature.elm

import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallCommand
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallEvent
import io.reactivex.Observable
import vivid.money.elmslie.core.Actor
import javax.inject.Inject

class StartCallActor @Inject constructor(): Actor<StartCallCommand, StartCallEvent> {
    override fun execute(command: StartCallCommand): Observable<StartCallEvent> {
        TODO("Not yet implemented")
    }
}