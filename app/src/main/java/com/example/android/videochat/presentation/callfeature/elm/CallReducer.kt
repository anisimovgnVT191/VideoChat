package com.example.android.videochat.presentation.callfeature.elm

import android.util.Log
import com.example.android.videochat.presentation.callfeature.elm.models.CallCommand
import com.example.android.videochat.presentation.callfeature.elm.models.CallEffect
import com.example.android.videochat.presentation.callfeature.elm.models.CallEvent
import com.example.android.videochat.presentation.callfeature.elm.models.CallState
import com.example.android.videochat.presentation.models.UserType
import vivid.money.elmslie.core.store.dsl_reducer.ScreenDslReducer
import javax.inject.Inject

class CallReducer @Inject constructor() :
    ScreenDslReducer<CallEvent, CallEvent.EventUi, CallEvent.EventInternal, CallState, CallEffect, CallCommand>(
        internalEventClass = CallEvent.EventInternal::class,
        uiEventClass = CallEvent.EventUi::class
    ) {
    override fun Result.internal(event: CallEvent.EventInternal) = when (event) {
        CallEvent.EventInternal.ErrorExchangingIceCandidates -> {

        }
        CallEvent.EventInternal.ErrorReceivingEventFromCallee -> {

        }
        is CallEvent.EventInternal.EventFromCalleeReceived -> {
            commands { +CallCommand.StartIceCandidateExchanging(event.callId, UserType.CALLER) }
        }
        CallEvent.EventInternal.IceCandidatesExchangingStarted -> {
            Log.e("IceCandidatesExchangingStarted", "exchanging")
            Unit
        }
    }

    override fun Result.ui(event: CallEvent.EventUi) = when (event) {
        is CallEvent.EventUi.InitCalleeUi -> {
            commands { +CallCommand.StartIceCandidateExchanging(event.callId, UserType.CALLEE) }
        }
        is CallEvent.EventUi.InitCallerUi -> {
            commands { +CallCommand.GetCalleeOffer(event.callId) }
        }
    }
}