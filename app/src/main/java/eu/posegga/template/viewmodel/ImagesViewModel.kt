package eu.posegga.template.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.posegga.template.common.RxViewModel
import eu.posegga.template.domain.model.Breed
import eu.posegga.template.domain.usecase.LoadImagesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ImagesViewModel(
    private val loadImagesUseCase: LoadImagesUseCase
) : RxViewModel() {

    private val _images: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }

    val images: LiveData<List<String>>
        get() = _images

    fun loadImagesForBreed(breed: Breed) {
        disposables += loadImagesUseCase.execute(
            params = LoadImagesUseCase.Params(
                breed = breed.breed,
                subBreed = breed.subBreed
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = _images::setValue,
                onError = Timber::e
            )

    }
}
