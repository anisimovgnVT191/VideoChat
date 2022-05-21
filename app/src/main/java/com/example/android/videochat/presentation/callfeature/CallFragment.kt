package com.example.android.videochat.presentation.callfeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.videochat.databinding.FragmentCallBinding
import com.example.android.videochat.presentation.models.UserType
import com.example.android.videochat.presentation.startcallfeature.StartCallFragment
import org.webrtc.*
import org.webrtc.voiceengine.WebRtcAudioManager

class CallFragment : Fragment() {
    private var _binding: FragmentCallBinding? = null
    private val binding: FragmentCallBinding
        get() = _binding!!

    private lateinit var callId: String
    private lateinit var userType: UserType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    companion object {
        private const val CALL_ID = "message_id"
        private const val USER_TYPE = "user_type"

        @JvmStatic
        fun newInstance(callId: String, userType: UserType) =
            StartCallFragment().apply {
                arguments = Bundle().apply {
                    putString(CALL_ID, callId)
                    putSerializable(USER_TYPE, userType)
                }
            }
    }
}