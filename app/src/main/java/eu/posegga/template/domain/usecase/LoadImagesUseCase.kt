package eu.posegga.template.domain.usecase

import eu.posegga.template.common.UseCase
import eu.posegga.template.data.repository.BreedRepository
import eu.posegga.template.data.repository.FavoriteRepository
import eu.posegga.template.domain.model.Favorite
import eu.posegga.template.domain.model.Image
import io.reactivex.Single

class LoadImagesUseCase(
    private val breedRepository: BreedRepository,
    private val favoriteRepository: FavoriteRepository
) : UseCase<LoadImagesUseCase.Params, Single<List<Image>>> {

    override fun execute(params: Params): Single<List<Image>> =
        favoriteRepository.loadFavorites()
            .flatMap { favorites: List<Favorite> ->
                loadBreedsOrSubBreeds(params).map { images: List<String> ->
                    mergeFavoritesAndImages(images, favorites)
                }
            }

    private fun mergeFavoritesAndImages(
        images: List<String>,
        favorites: List<Favorite>
    ): List<Image> =
        images.map { url ->
            Image(url = url, isFavorite = isListedInFavorites(favorites, url))
        }

    private fun isListedInFavorites(favorites: List<Favorite>, url: String) =
        favorites.find { it.imgUrl == url }?.let { true } ?: false

    private fun loadBreedsOrSubBreeds(params: Params) =
        params.subBreed?.let {
            breedRepository.loadImagesForSubBreed(
                params.breed,
                it
            )
        } ?: breedRepository.loadImagesForBreed(params.breed)

    data class Params(
        val breed: String,
        val subBreed: String?
    )
}
