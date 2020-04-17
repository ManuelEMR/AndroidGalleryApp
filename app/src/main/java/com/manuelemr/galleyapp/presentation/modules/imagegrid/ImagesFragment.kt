package com.manuelemr.galleyapp.presentation.modules.imagegrid

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.observe
import com.manuelemr.galleyapp.R
import com.manuelemr.galleyapp.data.modules.images.models.ImageData
import com.manuelemr.galleyapp.presentation.modules.imagedetail.ImageDetailFragment
import kotlinx.android.synthetic.main.fragment_images.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImagesFragment : Fragment(R.layout.fragment_images) {

    companion object {
        fun newInstance() =
            ImagesFragment()
    }

    private val viewModel: ImagesViewModel by viewModel()
    private var adapter: ImagesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupBindings()
    }

    private fun setupViews() {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        imageList.adapter = ImagesAdapter(::goToDetail)
            .also { adapter = it }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun setupBindings() {
        viewModel.loading.observe(viewLifecycleOwner) {
            swipeRefreshLayout.isRefreshing = it
        }

        viewModel.images.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }
    }

    private fun goToDetail(imageData: ImageData) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, ImageDetailFragment.newInstance(imageData.id))
            .addToBackStack(ImageDetailFragment::class.java.name)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}
