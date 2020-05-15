package eu.posegga.template.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.posegga.template.common.RxViewModel
import eu.posegga.template.common.subOnIoObserveMain
import eu.posegga.template.domain.model.Favorite
import eu.posegga.template.domain.usecase.LoadFavoritesUseCase
import eu.posegga.template.domain.usecase.RemoveFavoriteUseCase
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber

class FavoritesViewModel(
    private val loadFavoritesUseCase: LoadFavoritesUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : RxViewModel() {

    private val _favoritesLiveData: MutableLiveData<List<Favorite>> by lazy {
        MutableLiveData<List<Favorite>>()
    }

    val favoritesLiveData: LiveData<List<Favorite>>
        get() = _favoritesLiveData

    fun loadFavorites() {
        disposables += loadFavoritesUseCase.execute()
            .subOnIoObserveMain(
                onSuccess = _favoritesLiveData::setValue,
                onError = Timber::e
            )
    }

    fun onRemoveFavoriteClicked(favorite: Favorite) {
        disposables += removeFavoriteUseCase.execute(RemoveFavoriteUseCase.Params(favorite.imgUrl))
            .subOnIoObserveMain(
                onComplete = { updateFavorites(favorite) },
                onError = Timber::e
            )
    }

    private fun updateFavorites(favorite: Favorite) {
        _favoritesLiveData.value = _favoritesLiveData.value?.filterNot {
            it.imgUrl == favorite.imgUrl
        }
    }
}

