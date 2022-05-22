package com.example.android.videochat.presentation.callfeature.elm.models

sealed class CallEvent {
    sealed class EventUi : CallEvent() {
        class InitCallerUi(val callId: String) : EventUi()
        class InitCalleeUi(val callId: String) : EventUi()
    }

    sealed class EventInternal : CallEvent() {
        class EventFromCalleeReceived(val callId: String): EventInternal()
        object ErrorReceivingEventFromCallee : EventInternal()

        object IceCandidatesExchangingStarted : EventInternal()
        object ErrorExchangingIceCandidates : EventInternal()
    }
}