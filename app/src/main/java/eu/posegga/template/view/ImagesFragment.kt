package eu.posegga.template.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import eu.posegga.template.R
import eu.posegga.template.domain.model.Image
import eu.posegga.template.viewmodel.ImagesViewModel
import kotlinx.android.synthetic.main.details_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ImagesFragment : Fragment() {

    private val imagesViewModel: ImagesViewModel by viewModel()

    private val args: ImagesFragmentArgs by navArgs()

    private val imagesAdapter = ImagesAdapter().apply {
        onFavouriteClickListener = ::imageClicked
    }

    private fun imageClicked(image: Image) {
        imagesViewModel.onImageFavouriteClicked(image, args.breed.displayableName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.details_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        imagesViewModel.loadImagesForBreed(args.breed)
        observeImages()
    }

    private fun setupRecycler() {
        images_list.apply {
            setHasFixedSize(true)
            adapter = imagesAdapter
            layoutManager =
                GridLayoutManager(context, resources.getInteger(R.integer.images_columns))
        }
    }

    private fun observeImages() {
        imagesViewModel.images.observe(viewLifecycleOwner, Observer {
            imagesAdapter.submitList(it)
        })
    }
}
