package com.example.android.videochat.presentation.startcallfeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android.videochat.MainActivity
import com.example.android.videochat.R
import com.example.android.videochat.databinding.FragmentStartCallBinding
import com.example.android.videochat.di.AppComponentHolder
import com.example.android.videochat.presentation.callfeature.CallFragment
import com.example.android.videochat.presentation.models.UserType
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallCommand
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallEffect
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallEvent
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallState
import vivid.money.elmslie.android.base.ElmFragment
import vivid.money.elmslie.core.ElmStoreCompat
import javax.inject.Inject


class StartCallFragment : ElmFragment<StartCallEvent, StartCallEffect, StartCallState>() {
    private var _binding: FragmentStartCallBinding? = null
    private val binding: FragmentStartCallBinding
        get() = _binding!!

    @Inject
    lateinit var startCallStore: ElmStoreCompat<StartCallEvent, StartCallState, StartCallEffect, StartCallCommand>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppComponentHolder.get().injectStartCallFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartCallBinding.inflate(inflater)

        binding.startCallButton.setOnClickListener {
            val callId = binding.callIdField.text.toString()
            store.accept(StartCallEvent.EventUI.StartCallButtonPressed(callId))
        }

        binding.joinCallButton.setOnClickListener {
            val callId = binding.callIdField.text.toString()
            store.accept(StartCallEvent.EventUI.JoinCallButtonPressed(callId))
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val initEvent: StartCallEvent
        get() = StartCallEvent.EventUI.InitUiEvent

    override fun render(state: StartCallState) {

    }

    override fun handleEffect(effect: StartCallEffect) = when (effect) {
        is StartCallEffect.NavigateToCallScreen -> {
            navigateToCallScreen(effect.callId, effect.userType)
        }
    }

    override fun createStore() = startCallStore

    private fun navigateToCallScreen(callId: String, userType: UserType) {
        (requireActivity() as MainActivity).navigateToCallFragment(callId, userType)
    }
}