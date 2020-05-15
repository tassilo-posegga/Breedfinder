package eu.posegga.template.data.repository

import eu.posegga.template.domain.model.Favorite
import eu.posegga.template.local.repository.FavoriteLocalSource
import io.reactivex.Single

class FavoriteRepository(
    private val local: FavoriteLocalSource
) {

    fun loadFavorites(): Single<List<Favorite>> =
        local.loadFavorites()

    fun addFavorite(favorite: Favorite) =
        local.addFavorite(favorite)

    fun removeFavorite(imgUrl: String) =
        local.removeFavorite(imgUrl)
}