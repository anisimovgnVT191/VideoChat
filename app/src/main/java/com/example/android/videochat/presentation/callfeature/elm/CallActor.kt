package com.example.android.videochat.presentation.callfeature.elm

import com.example.android.videochat.domain.CallRepository
import com.example.android.videochat.presentation.callfeature.elm.models.CallCommand
import com.example.android.videochat.presentation.callfeature.elm.models.CallEvent
import io.reactivex.Observable
import vivid.money.elmslie.core.Actor
import javax.inject.Inject

class CallActor @Inject constructor(
    private val repository: CallRepository
): Actor<CallCommand, CallEvent> {
    override fun execute(command: CallCommand) = when (command) {
        is CallCommand.GetCalleeOffer -> {
            repository.getCalleeResponse(command.callId).mapEvents(
                eventMapper = { CallEvent.EventInternal.EventFromCalleeReceived(command.callId) },
                errorMapper = { CallEvent.EventInternal.ErrorReceivingEventFromCallee }
            )
        }
        is CallCommand.StartIceCandidateExchanging -> {
            repository.startIceCandidatesExchange(command.callId, command.userType).mapEvents(
                eventMapper = { CallEvent.EventInternal.IceCandidatesExchangingStarted },
                errorMapper = { CallEvent.EventInternal.ErrorExchangingIceCandidates }
            )
        }
    }
}