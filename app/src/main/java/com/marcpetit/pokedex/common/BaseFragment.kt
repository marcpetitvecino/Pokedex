package com.marcpetit.pokedex.common

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.lang.IllegalStateException

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    private var _binding: T? = null

    protected var binding: T
        get() = requireBinding()
        set(value) {
            _binding = value
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun requireBinding(): T {
        _binding?.let {
            return it
        } ?: throw IllegalStateException("Binding is null at ${this::class}")
    }

    fun showErrorMessage(message: String) {
        Snackbar.make(
            binding.root,
            message,
            BaseTransientBottomBar.LENGTH_SHORT
        ).show()
    }
}
