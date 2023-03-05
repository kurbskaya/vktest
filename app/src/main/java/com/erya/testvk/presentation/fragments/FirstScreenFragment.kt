package com.erya.testvk.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.erya.testvk.R
import com.erya.testvk.data.datasource.GifDataSource
import com.erya.testvk.data.repository.GifRepositoryImpl
import com.erya.testvk.databinding.FirstScreenBinding
import com.erya.testvk.network.GifApi
import com.erya.testvk.presentation.adapters.GifAdapter
import com.erya.testvk.presentation.models.UiItemError
import com.erya.testvk.presentation.viewmodels.FirstScreenViewModel
import com.erya.testvk.presentation.viewmodels.ViewModelFactory

class FirstScreenFragment : Fragment() {
    private var _binding: FirstScreenBinding?= null
    private val binding get() = _binding!!

    private lateinit var viewModel: FirstScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FirstScreenBinding.inflate(inflater, container, false)
        val retrofitService = GifApi.getInstance()
        val gifDataSource = GifDataSource(retrofitService)
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(
                GifRepositoryImpl(
                    gifDataSource
                )
            )
        )[FirstScreenViewModel::class.java]
        viewModel.getRandomGiphs()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lifecycleOwner = viewLifecycleOwner

        viewModel.randomGifsLiveData.observe(lifecycleOwner) { uiItemError ->
            when (uiItemError) {
                is UiItemError.Success -> {
                    binding.progress.visibility = View.GONE
                    val giphAdapter = binding.rv.adapter
                    if (giphAdapter == null) {
                        val myAdapter = GifAdapter(
                            onItemClick = {
                                val fragment = SecondScreenFragment.newInstance(it.id!!, it.url!!, it.title!!)
                                requireActivity().supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, fragment)
                                    .addToBackStack(null)
                                    .commit()
                            }
                        )
                        binding.rv.adapter = myAdapter
                        binding.rv.layoutManager = GridLayoutManager(requireContext(),3)
                        myAdapter.submitList(uiItemError.elements)
                    } else {
                        val myAdapter = giphAdapter as GifAdapter
                        myAdapter.submitList(uiItemError.elements)
                    }
                }
                is UiItemError.Error -> {
                    Log.d("TAG", uiItemError.exception.toString())
                }
                is UiItemError.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        }

        binding.button.setOnClickListener {
            val question = binding.editTextView.text.toString()
            if (question == "") {
                Toast.makeText(context, "Empty request", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.clearLiveData()
                viewModel.getSpecialGif(question)
                val context = context
                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(binding.editTextView.windowToken, 0)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        viewModel.clearLiveData()
        super.onDestroyView()
    }
}