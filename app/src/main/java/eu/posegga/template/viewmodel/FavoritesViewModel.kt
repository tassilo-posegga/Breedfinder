package eu.posegga.template.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.posegga.template.common.RxViewModel
import eu.posegga.template.common.subOnIoObserveMain
import eu.posegga.template.domain.model.Favorite
import eu.posegga.template.domain.model.FavoriteListItem
import eu.posegga.template.domain.model.FavoriteTitle
import eu.posegga.template.domain.usecase.LoadFavoritesUseCase
import eu.posegga.template.domain.usecase.RemoveFavoriteUseCase
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber

class FavoritesViewModel(
    private val loadFavoritesUseCase: LoadFavoritesUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : RxViewModel() {

    private val _favoritesLiveData: MutableLiveData<List<FavoriteListItem>> by lazy {
        MutableLiveData<List<FavoriteListItem>>()
    }

    val favoritesLiveData: LiveData<List<FavoriteListItem>>
        get() = _favoritesLiveData

    fun loadFavorites() {
        disposables += loadFavoritesUseCase.execute()
            .subOnIoObserveMain(
                onSuccess = { prepareData(it) },
                onError = Timber::e
            )
    }

    // TODO extract to a usecase or actually map it to a view layer model
    private fun prepareData(favorites: List<Favorite>) {
        val newList: MutableList<FavoriteListItem> = mutableListOf()
        favorites.groupBy { it.displayableName }.forEach {
            newList.add(FavoriteTitle(it.key))
            newList.addAll(it.value)
        }
        _favoritesLiveData.value = newList
    }

    // TODO need to remove titles when all favorites of a breed were deleted
    fun onRemoveFavoriteClicked(favorite: Favorite) {
        disposables += removeFavoriteUseCase.execute(RemoveFavoriteUseCase.Params(favorite.imgUrl))
            .subOnIoObserveMain(
                onComplete = { removeFavoriteFromLiveData(favorite) },
                onError = Timber::e
            )
    }

    private fun removeFavoriteFromLiveData(favorite: Favorite) {
        _favoritesLiveData.value = _favoritesLiveData.value?.run {
            filterNot {
                (it as? Favorite)?.imgUrl == favorite.imgUrl
            }
        }
    }
}

