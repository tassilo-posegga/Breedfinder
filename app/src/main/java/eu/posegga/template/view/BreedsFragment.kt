package eu.posegga.template.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import eu.posegga.template.R
import eu.posegga.template.domain.model.Breed
import eu.posegga.template.viewmodel.BreedsViewModel
import kotlinx.android.synthetic.main.breed_fragment.breed_list
import org.koin.android.ext.android.inject

class BreedsFragment : Fragment() {

    private val breedsViewModel: BreedsViewModel by inject()

    private val listAdapter = BreedsAdapter().apply {
        breedClickListener = ::breedClicked
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.breed_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupRecycler()
        breedsViewModel.loadBreeds()
        observeBreeds()
    }

    private fun setupRecycler() {
        breed_list.apply {
            setHasFixedSize(true)
            adapter = listAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
    }

    private fun observeBreeds() {
        breedsViewModel.breedsLiveData.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }

    private fun breedClicked(breed: Breed) {
        findNavController().navigate(
            BreedsFragmentDirections.actionListFragmentToDetailFragment(breed)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.breed_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_to_favorites -> {
                findNavController().navigate(R.id.favoritesFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
