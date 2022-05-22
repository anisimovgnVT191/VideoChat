package com.example.android.videochat.presentation.startcallfeature.elm.models

sealed class StartCallCommand {
    class StartCall(val callId: String) : StartCallCommand()
    class JoinCall(val callId: String) : StartCallCommand()
}
