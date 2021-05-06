package com.klobalvar.pokedex.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.klobalvar.pokedex.core.extensions.binding
import com.klobalvar.pokedex.core.extensions.initBinding
import com.skydoves.transformationlayout.TransformationAppCompatActivity

abstract class BindingTransformationActivity<T : ViewDataBinding> constructor(
    @LayoutRes private val layoutResId: Int
) : TransformationAppCompatActivity() {

    private var _binding: T? = null
    protected val binding: T get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = binding(layoutResId)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.unbind()
        _binding = null
    }

    protected inline fun initBinding(initBlock: T.() -> Unit) =
        binding.initBinding(initBlock)
}