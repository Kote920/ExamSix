package com.example.examsix.presentation.authorization

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Rect
import android.hardware.biometrics.BiometricPrompt.CryptoObject
import android.os.Build
import android.util.Log.d
import android.view.View

import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examsix.R
import com.example.examsix.databinding.FragmentAuthorizationBinding
import com.example.examsix.di.StaticDataProvider
import com.example.examsix.presentation.BaseFragment
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties

@AndroidEntryPoint
class AuthorizationFragment :
    BaseFragment<FragmentAuthorizationBinding>(FragmentAuthorizationBinding::inflate) {
    private val KEY_NAME = "my_key_alias"
    private lateinit var cipher: Cipher
    private lateinit var adapter: AuthorizationRecyclerAdapter
    private var input = mutableListOf<Int>()
    private lateinit var cryptoObject: FingerprintManagerCompat.CryptoObject

    @Inject
    lateinit var staticDataProvider: StaticDataProvider


    override fun setUp() {
        initItemRecycler()

    }

    override fun listeners() {

    }

    private fun initItemRecycler() {

        adapter = AuthorizationRecyclerAdapter {
            if(input.isNotEmpty()){
            input.removeAt(input.lastIndex)
            dotsManagement()
            }
        }

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


        for (i in dotImageViews.indices) {
            if (i < input.size) {
                dotImageViews[i].setImageResource(R.drawable.green)
            } else {
                dotImageViews[i].setImageResource(R.drawable.grey)
            }
        }
    }

}

