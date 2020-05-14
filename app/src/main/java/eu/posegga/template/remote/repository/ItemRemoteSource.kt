package eu.posegga.template.remote.repository

import eu.posegga.template.domain.model.Breed
import eu.posegga.template.remote.api.ItemApi
import eu.posegga.template.remote.mapper.RemoteImagesMapper
import eu.posegga.template.remote.mapper.RemoteItemMapper
import io.reactivex.Single

class ItemRemoteSource(
    private val itemApi: ItemApi,
    private val remoteItemMapper: RemoteItemMapper,
    private val remoteImagesMapper: RemoteImagesMapper
) {

    fun loadItems(): Single<List<Breed>> =
        itemApi.loadItems().map(remoteItemMapper::map)

    fun loadImagesForBreed(breed: String): Single<List<String>> =
        itemApi.loadImagesForBreed(breed)
            .map(remoteImagesMapper::map)

    fun loadImagesForSubBreed(breed: String, subBreed: String): Single<List<String>> =
        itemApi.loadImagesForSubBreed(breed = breed, subBreed = subBreed)
            .map(remoteImagesMapper::map)
}
