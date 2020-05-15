package eu.posegga.template.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import eu.posegga.template.R
import eu.posegga.template.domain.model.Favorite
import eu.posegga.template.domain.model.FavoriteListItem
import eu.posegga.template.view.FavoritesAdapter.Companion.VIEW_TYPE_HEADER
import eu.posegga.template.viewmodel.FavoritesViewModel
import kotlinx.android.synthetic.main.favorites_fragment.*
import org.koin.android.ext.android.inject

class FavoritesFragment : Fragment() {

    private val favoritesViewModel: FavoritesViewModel by inject()

    private val favoritesAdapter = FavoritesAdapter<FavoriteListItem>().apply {
        onFavoriteClickListener = ::itemClicked
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.favorites_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        favoritesViewModel.loadFavorites()
        observeItems()
    }

    private fun initRecycler() {
        val columns = resources.getInteger(R.integer.images_columns)
        favorites_list.apply {
            setHasFixedSize(true)
            adapter = favoritesAdapter
            layoutManager =
                GridLayoutManager(context, columns).apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int =
                            when (favoritesAdapter.getItemViewType(position)) {
                                VIEW_TYPE_HEADER -> columns
                                else -> 1
                            }
                    }
                }
            addItemDecoration(SpacesItemDecoration(columns))
        }
    }

    private fun observeItems() {
        favoritesViewModel.favoritesLiveData.observe(viewLifecycleOwner, Observer {
            favoritesAdapter.submitList(it)
        })
    }

    private fun itemClicked(favorite: Favorite) {
        favoritesViewModel.onRemoveFavoriteClicked(favorite)
    }
}
