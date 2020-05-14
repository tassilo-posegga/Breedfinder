package eu.posegga.template.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.posegga.template.common.RxViewModel
import eu.posegga.template.data.repository.ItemRepository
import eu.posegga.template.domain.model.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ListViewModel(
    private val repository: ItemRepository
) : RxViewModel() {

    private val _itemsLiveData: MutableLiveData<List<Item>> by lazy {
        MutableLiveData<List<Item>>()
    }

    val itemsLiveData: LiveData<List<Item>>
        get() = _itemsLiveData

    fun loadItems() {
        disposables += repository.loadItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = ::handleSuccess,
                onError = Timber::e
            )
    }

    private fun handleSuccess(items: List<Item>) {
        _itemsLiveData.value = items
    }
}
