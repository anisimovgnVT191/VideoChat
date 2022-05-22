package com.example.android.videochat.presentation.callfeature.elm.models

sealed class CallEvent {
    sealed class EventUi : CallEvent() {
        class InitCallerUi(val callId: String)
        class InitCalleeUi(val callId: String)
    }

    sealed class EventInternal : CallEvent() {}
}