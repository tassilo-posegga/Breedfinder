package eu.posegga.template.local.repository

import eu.posegga.template.domain.model.Favorite
import eu.posegga.template.local.db.FavoriteDao
import eu.posegga.template.local.mapper.LocalFavoriteMapper
import io.reactivex.Completable
import io.reactivex.Single

class FavoriteLocalSource(
    private val favoriteDao: FavoriteDao,
    private val favoriteMapper: LocalFavoriteMapper
) {

    private val favorites: MutableList<Favorite> = mutableListOf()

    fun loadFavorites(): Single<List<Favorite>> =
        favoriteDao.loadFavorites().map {
            it.map(favoriteMapper::to)
        }

    fun addFavorite(favorite: Favorite): Completable =
        favoriteDao.addFavorite(favoriteMapper.from(favorite))

    fun removeFavorite(imgUrl: String): Completable =
        favoriteDao.removeFavorite(imgUrl)
}
