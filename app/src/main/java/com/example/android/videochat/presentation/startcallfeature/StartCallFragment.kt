package com.example.android.videochat.presentation.startcallfeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.videochat.R
import com.example.android.videochat.databinding.FragmentStartCallBinding


class StartCallFragment : Fragment() {
    private var _binding: FragmentStartCallBinding? = null
    private val binding: FragmentStartCallBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartCallBinding.inflate(inflater)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}