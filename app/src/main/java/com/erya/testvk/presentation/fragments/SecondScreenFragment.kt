package com.erya.testvk.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.erya.testvk.databinding.SecondFragmentBinding

class SecondScreenFragment : Fragment() {
    private lateinit var _binding: SecondFragmentBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SecondFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg1 = arguments?.getString(ID_GIF)
        val arg2 = arguments?.getString(URL_GIF)
        val arg3 = arguments?.getString(TITLE_GIF)

        binding.tv1.text = "Title: $arg3"
        binding.tv2.text = "Id: $arg1"
        Glide.with(binding.gifImageScreen.context)
            .asGif()
            .load(arg2)
            .into(binding.gifImageScreen)
    }

    companion object {
        private const val ID_GIF = "id_gif"
        private const val URL_GIF = "url_gif"
        private const val TITLE_GIF = "title_gif"

        fun newInstance(arg1: String, arg2: String, arg3: String) = SecondScreenFragment().apply {
            arguments = Bundle().apply {
                putString(ID_GIF, arg1)
                putString(URL_GIF, arg2)
                putString(TITLE_GIF, arg3)
            }
        }
    }
}