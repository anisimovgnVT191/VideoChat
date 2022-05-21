package com.example.android.videochat.presentation.callfeature.elm

import com.example.android.videochat.presentation.callfeature.elm.models.CallCommand
import com.example.android.videochat.presentation.callfeature.elm.models.CallEffect
import com.example.android.videochat.presentation.callfeature.elm.models.CallEvent
import com.example.android.videochat.presentation.callfeature.elm.models.CallState
import vivid.money.elmslie.core.store.dsl_reducer.ScreenDslReducer
import javax.inject.Inject

class CallReducer @Inject constructor() :
    ScreenDslReducer<CallEvent, CallEvent.EventUi, CallEvent.EventInternal, CallState, CallEffect, CallCommand>(
        internalEventClass = CallEvent.EventInternal::class,
        uiEventClass = CallEvent.EventUi::class
    ) {
    override fun Result.internal(event: CallEvent.EventInternal): Any? {
        TODO("Not yet implemented")
    }

    override fun Result.ui(event: CallEvent.EventUi): Any? {
        TODO("Not yet implemented")
    }
}