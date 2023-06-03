package com.sample.viewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.viewer.databinding.ViewerActivityBinding
import com.sample.viewer.injection.injectUIFeatures

class ViewerActivity : AppCompatActivity() {

    init {
        injectUIFeatures()
    }

    lateinit var binding: ViewerActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
