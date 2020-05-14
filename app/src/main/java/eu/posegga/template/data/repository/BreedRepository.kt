package eu.posegga.template.data.repository

import eu.posegga.template.domain.model.Breed
import eu.posegga.template.remote.repository.BreedRemoteSource
import io.reactivex.Single

class BreedRepository(
    private val remote: BreedRemoteSource
) {

    fun loadItems(): Single<List<Breed>> =
        remote.loadItems()

    fun loadImagesForBreed(breed: String): Single<List<String>> =
        remote.loadImagesForBreed(breed)

    fun loadImagesForSubBreed(breed: String, subBreed: String) =
        remote.loadImagesForSubBreed(breed = breed, subBreed = subBreed)
}
