package eu.posegga.template.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import eu.posegga.template.R
import eu.posegga.template.domain.model.Breed
import eu.posegga.template.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.breed_fragment.*
import org.koin.android.ext.android.inject


class BreedsFragment : Fragment() {

    private val listViewModel: ListViewModel by inject()

    private val listAdapter = BreedsAdapter().apply {
        itemClickListener = ::itemClicked
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.breed_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        listViewModel.loadItems()
        observeItems()
    }

    private fun initRecycler() {
        breed_list.apply {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun observeItems() {
        listViewModel.itemsLiveData.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }

    private fun itemClicked(breed: Breed) {
        findNavController().navigate(
            BreedsFragmentDirections.actionListFragmentToDetailFragment(breed)
        )
    }
}
