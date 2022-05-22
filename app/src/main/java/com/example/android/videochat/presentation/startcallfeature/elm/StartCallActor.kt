package com.example.android.videochat.presentation.startcallfeature.elm

import com.example.android.videochat.data.CallRepositoryImpl
import com.example.android.videochat.domain.CallRepository
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallCommand
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallEvent
import io.reactivex.Observable
import vivid.money.elmslie.core.Actor
import javax.inject.Inject

class StartCallActor @Inject constructor(
    private val repository: CallRepository
) : Actor<StartCallCommand, StartCallEvent> {
    override fun execute(command: StartCallCommand): Observable<StartCallEvent> = when (command) {
        is StartCallCommand.JoinCall -> {
            repository.joinCall(command.callId).mapEvents(
                eventMapper = { StartCallEvent.EventInternal.CallJoined(command.callId) },
                errorMapper = { StartCallEvent.EventInternal.ErrorJoiningCall }
            )
        }

        is StartCallCommand.StartCall -> {
            repository.startCall(command.callId).mapEvents(
                eventMapper = { StartCallEvent.EventInternal.CallStarted(command.callId) },
                errorMapper = { StartCallEvent.EventInternal.ErrorStartingCall }
            )
        }
    }
}