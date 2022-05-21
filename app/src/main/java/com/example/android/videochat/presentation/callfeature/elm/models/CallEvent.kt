package com.example.android.videochat.presentation.callfeature.elm.models

sealed class CallEvent {
    sealed class EventUi : CallEvent() {}
    sealed class EventInternal : CallEvent() {}
}