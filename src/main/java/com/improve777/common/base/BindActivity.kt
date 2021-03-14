package com.improve777.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.improve777.common.livedata.ViewLifecycleHolder

abstract class BindActivity<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
) : AppCompatActivity(), ViewLifecycleHolder {

    override val viewLifecycleHolder: LifecycleOwner
        get() = this

    private var _binding: B? = null
    protected val binding: B
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}