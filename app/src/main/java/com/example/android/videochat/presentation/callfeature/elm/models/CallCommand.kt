package com.example.android.videochat.presentation.callfeature.elm.models

import com.example.android.videochat.presentation.models.UserType

sealed class CallCommand {
    class GetCalleeOffer(val callId: String): CallCommand()
    class StartIceCandidateExchanging(val callId: String, userType: UserType): CallCommand()
}