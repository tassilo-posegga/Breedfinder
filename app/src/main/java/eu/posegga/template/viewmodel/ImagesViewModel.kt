package eu.posegga.template.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.posegga.template.common.RxViewModel
import eu.posegga.template.common.subOnIoObserveMain
import eu.posegga.template.domain.model.Breed
import eu.posegga.template.domain.model.Favorite
import eu.posegga.template.domain.model.Image
import eu.posegga.template.domain.usecase.AddFavoriteUseCase
import eu.posegga.template.domain.usecase.LoadImagesUseCase
import eu.posegga.template.domain.usecase.RemoveFavoriteUseCase
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber

class ImagesViewModel(
    private val loadImagesUseCase: LoadImagesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : RxViewModel() {

    private val _images: MutableLiveData<List<Image>> by lazy {
        MutableLiveData<List<Image>>()
    }

    val images: LiveData<List<Image>>
        get() = _images

    fun loadImagesForBreed(breed: Breed) {
        disposables += loadImagesUseCase.execute(
            params = LoadImagesUseCase.Params(
                breed = breed.breed,
                subBreed = breed.subBreed
            )
        )
            .subOnIoObserveMain(
                onSuccess = _images::setValue,
                onError = Timber::e
            )
    }

    fun onImageFavoriteClicked(image: Image, displayableName: String) {
        if (image.isFavorite) {
            removeFavorite(image)
        } else {
            addFavorite(image, displayableName)
        }
    }

    private fun removeFavorite(image: Image) {
        disposables += removeFavoriteUseCase.execute(
            RemoveFavoriteUseCase.Params(imgUrl = image.url)
        ).subOnIoObserveMain(
            onComplete = { setFavoriteStatus(image, false) },
            onError = Timber::e
        )
    }

    private fun addFavorite(image: Image, displayableName: String) {
        disposables += addFavoriteUseCase.execute(
            Favorite(
                displayableName = displayableName,
                imgUrl = image.url
            )
        )
            .subOnIoObserveMain(
                onComplete = { setFavoriteStatus(image, true) },
                onError = Timber::e
            )
    }

    private fun setFavoriteStatus(image: Image, checked: Boolean) {
        _images.value = _images.value?.map {
            if (image.url == it.url) {
                image.copy(
                    isFavorite = checked
                )
            } else {
                it
            }
        }
    }
}
