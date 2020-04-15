package com.manuelemr.galleyapp.presentation.modules.imagedetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.manuelemr.galleyapp.R
import com.manuelemr.galleyapp.presentation.foundation.extensions.load
import kotlinx.android.synthetic.main.fragment_image_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageDetailFragment : Fragment(R.layout.fragment_image_detail) {

    companion object {
        fun newInstance(id: String) = ImageDetailFragment().apply {
            arguments = bundleOf("id" to id)
        }
    }

    private val viewModel: ImageDetailViewModel by viewModel()
    private val imageId: String by lazy {
        // we want it to crash if no ID is set! programmer's mistake
        arguments!!.getString("id")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupBindings()
    }

    private fun setupViews() {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupBindings() {
        viewModel.getImageDetail(imageId)

        viewModel.image.observe(viewLifecycleOwner) {
            imageView.load(it.fullPicture?: it.croppedPicture)
            title.text = it.author
            cameraType.text = it.camera
        }
    }
}
