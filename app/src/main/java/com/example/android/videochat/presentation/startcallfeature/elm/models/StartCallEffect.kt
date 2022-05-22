package com.example.android.videochat.presentation.startcallfeature.elm.models

import com.example.android.videochat.presentation.models.UserType

sealed class StartCallEffect {
    class NavigateToCallScreen(val callId: String, val userType: UserType): StartCallEffect()
}
