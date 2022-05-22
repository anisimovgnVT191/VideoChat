package com.example.android.videochat.presentation.callfeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.videochat.databinding.FragmentCallBinding
import com.example.android.videochat.di.AppComponentHolder
import com.example.android.videochat.presentation.callfeature.elm.models.CallCommand
import com.example.android.videochat.presentation.callfeature.elm.models.CallEffect
import com.example.android.videochat.presentation.callfeature.elm.models.CallEvent
import com.example.android.videochat.presentation.callfeature.elm.models.CallState
import com.example.android.videochat.presentation.models.UserType
import com.example.android.videochat.presentation.startcallfeature.StartCallFragment
import vivid.money.elmslie.android.base.ElmFragment
import vivid.money.elmslie.core.ElmStoreCompat
import javax.inject.Inject

class CallFragment : ElmFragment<CallEvent, CallEffect, CallState>() {
    private var _binding: FragmentCallBinding? = null
    private val binding: FragmentCallBinding
        get() = _binding!!

    private lateinit var callId: String
    private lateinit var userType: UserType

    @Inject
    lateinit var callStore: ElmStoreCompat<CallEvent, CallState, CallEffect, CallCommand>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppComponentHolder.get().injectCallFragment(this)

        arguments?.let {
            callId = it.getString(CALL_ID) ?: error("No way to process a call without callID")
            userType = it.getSerializable(USER_TYPE) as? UserType
                ?: error("No way to process a call without user type")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCallBinding.inflate(inflater)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val initEvent: CallEvent
        get() = when (userType) {
            UserType.CALLER -> CallEvent.EventUi.InitCallerUi(callId)
            UserType.CALLEE -> CallEvent.EventUi.InitCalleeUi(callId)
        }

    override fun render(state: CallState) {

    }

    override fun createStore() = callStore

    override fun handleEffect(effect: CallEffect): Unit? {
        return super.handleEffect(effect)
    }

    companion object {
        private const val CALL_ID = "message_id"
        private const val USER_TYPE = "user_type"

        @JvmStatic
        fun newInstance(callId: String, userType: UserType) =
            CallFragment().apply {
                arguments = Bundle().apply {
                    putString(CALL_ID, callId)
                    putSerializable(USER_TYPE, userType)
                }
            }
    }
}