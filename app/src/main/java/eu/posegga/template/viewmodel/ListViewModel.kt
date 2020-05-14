package eu.posegga.template.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.posegga.template.common.RxViewModel
import eu.posegga.template.domain.model.Breed
import eu.posegga.template.domain.usecase.LoadItemsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ListViewModel(
    private val loadItemsUseCase: LoadItemsUseCase
) : RxViewModel() {

    private val _itemsLiveData: MutableLiveData<List<Breed>> by lazy {
        MutableLiveData<List<Breed>>()
    }

    val itemsLiveData: LiveData<List<Breed>>
        get() = _itemsLiveData

    fun loadItems() {
        disposables += loadItemsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = ::handleSuccess,
                onError = Timber::e
            )
    }

    private fun handleSuccess(breeds: List<Breed>) {
        _itemsLiveData.value = breeds
    }
}
