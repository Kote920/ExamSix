package com.example.examsix.presentation.authorization

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examsix.databinding.ItemRecyclerviewBinding
import com.example.examsix.databinding.ItemRecyclerviewFingerprintBinding
import com.example.examsix.databinding.ItemRecyclerviewNumberBinding
import com.example.examsix.domain.Item
import com.example.examsix.domain.Type




class AuthorizationRecyclerAdapter :
    ListAdapter<Item, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == NUMBER_TYPE) {
            return NumberViewHolder(
                ItemRecyclerviewNumberBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else if (viewType == REMOVE_TYPE) {
            return RemoveViewHolder(
                ItemRecyclerviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return FingerprintViewHolder(
                ItemRecyclerviewFingerprintBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NumberViewHolder) {
            holder.bind()
        } else if (holder is RemoveViewHolder) {
            holder.bind()
        } else if (holder is FingerprintViewHolder) {
            holder.bind()
        }
    }

    companion object {
        const val NUMBER_TYPE = 1
        const val FINGERPRINT_TYPE = 2
        const val REMOVE_TYPE = 3

    }

    lateinit var itemOnClick: (Item) -> Unit
    lateinit var itemOnClickClear: () -> Unit



    override fun getItemViewType(position: Int): Int {
        if (currentList[position].type == Type.NUMBER) {
            return NUMBER_TYPE
        } else if (currentList[position].type == Type.REMOVE) {
            return REMOVE_TYPE

        } else {
            return FINGERPRINT_TYPE
        }

    }

    inner class NumberViewHolder(private val binding: ItemRecyclerviewNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = currentList[adapterPosition]
            binding.tvNumber.text = item.number.toString()
            listeners(item)
        }

        private fun listeners(item: Item) {
            binding.root.setOnClickListener {
                itemOnClick.invoke(item)
            }

        }

    }

    inner class RemoveViewHolder(private val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            listeners()
        }

        private fun listeners() {
            binding.root.setOnClickListener {
                itemOnClickClear.invoke()
            }

        }

    }

    inner class FingerprintViewHolder(private val binding: ItemRecyclerviewFingerprintBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
//        private fun authenticateUserWithFingerprint() {
//            val promptInfo = BiometricPrompt.PromptInfo.Builder()
//                .setTitle("Fingerprint Authentication")
//                .setSubtitle("Authenticate using your fingerprint")
//                .setNegativeButtonText("Cancel")
//                .build()
//
//            val biometricPrompt = BiometricPrompt(
//                this,
//                mainExecutor,
//                object : BiometricPrompt.AuthenticationCallback() {
//                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
//                        super.onAuthenticationError(errorCode, errString)
//                        // Handle authentication error
//                    }
//
//                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
//                        super.onAuthenticationSucceeded(result)
//                        // Authentication successful
//                        // Proceed with necessary actions after successful authentication
//                    }
//
//                    override fun onAuthenticationFailed() {
//                        super.onAuthenticationFailed()
//                        // Authentication failed
//                    }
//                })
//
//            biometricPrompt.authenticate(promptInfo)
//        }

        private fun listeners(item: Item) {
            binding.root.setOnClickListener {
//                authenticateUserWithFingerprint()
            }

        }

    }
}