package eu.posegga.template.remote.repository

import eu.posegga.template.domain.model.Breed
import eu.posegga.template.remote.api.DogApi
import eu.posegga.template.remote.mapper.RemoteBreedMapper
import eu.posegga.template.remote.mapper.RemoteImagesMapper
import io.reactivex.Single

class BreedRemoteSource(
    private val dogApi: DogApi,
    private val remoteBreedMapper: RemoteBreedMapper,
    private val remoteImagesMapper: RemoteImagesMapper
) {

    fun loadBreeds(): Single<List<Breed>> =
        dogApi.loadBreeds().map(remoteBreedMapper::map)

    fun loadImagesForBreed(breed: String): Single<List<String>> =
        dogApi.loadImagesForBreed(breed)
            .map(remoteImagesMapper::map)

    fun loadImagesForSubBreed(breed: String, subBreed: String): Single<List<String>> =
        dogApi.loadImagesForSubBreed(breed = breed, subBreed = subBreed)
            .map(remoteImagesMapper::map)
}
