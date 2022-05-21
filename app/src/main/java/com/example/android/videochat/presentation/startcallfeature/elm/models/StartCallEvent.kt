package com.example.android.videochat.presentation.startcallfeature.elm.models

sealed class StartCallEvent {
    sealed class EventUI : StartCallEvent() {}
    sealed class EventInternal : StartCallEvent() {}
}
