package eu.posegga.template.local.repository

import eu.posegga.template.domain.model.Favorite
import io.reactivex.Completable
import io.reactivex.Single

class FavoriteLocalSource {

    private val favorites: MutableList<Favorite> = mutableListOf()

    fun loadFavorites(): Single<List<Favorite>> =
        Single.just(favorites)

    fun addFavorite(favorite: Favorite): Completable =
        Completable.fromAction {
            favorites.add(favorite)
        }

    fun removeFavorite(imgUrl: String): Completable =
        Completable.fromAction {
            favorites.remove(favorites.find { it.imgUrl == imgUrl })
        }

}
