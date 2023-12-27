package com.example.examsix.presentation.authorization

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.examsix.R
import com.example.examsix.databinding.FragmentAuthorizationBinding
import com.example.examsix.di.StaticDataProvider
import com.example.examsix.presentation.BaseFragment
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthorizationFragment :
    BaseFragment<FragmentAuthorizationBinding>(FragmentAuthorizationBinding::inflate) {

    private lateinit var adapter: AuthorizationRecyclerAdapter
    private var input = mutableListOf<Int>()
    @Inject
    lateinit var staticDataProvider: StaticDataProvider


    override fun setUp() {
        initItemRecycler()

    }

    override fun listeners() {

    }

    private fun initItemRecycler() {

        adapter = AuthorizationRecyclerAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = adapter
        adapter.apply {
            submitList(staticDataProvider.getStaticData())
            itemOnClick = {
                input.add(it.number!!)
                if (input.size <= 4) {
                    dotsManagement()
                    if (input.size == 4) {
                        validatePassword()
                    }
                }
            }


            itemOnClickClear = {
                // Other logic...
                dotsManagement()
            }

        }

    }

    private fun validatePassword() {
        if (input.joinToString("") == "0934") {
            Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()
            input.clear()
            dotsManagement()
        } else {
            Toast.makeText(requireContext(), "Failure!", Toast.LENGTH_SHORT).show()
            input.clear()
            dotsManagement()
        }
    }
    private fun dotsManagement() {
        val dotImageViews = arrayOf(
            binding.ivDigit1,
            binding.ivDigit2,
            binding.ivDigit3,
            binding.ivDigit4
        )

        // Loop through dotImageViews and set the appropriate image resource
        for (i in dotImageViews.indices) {
            if (i < input.size) {
                dotImageViews[i].setImageResource(R.drawable.green) // Use your green dot drawable
            } else {
                dotImageViews[i].setImageResource(R.drawable.grey) // Use your grey dot drawable
            }
        }
    }
}