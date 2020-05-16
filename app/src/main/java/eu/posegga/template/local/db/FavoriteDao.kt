package eu.posegga.template.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.posegga.template.local.model.LocalFavorite
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(favorite: LocalFavorite): Completable

    @Query("SELECT * FROM favorites")
    fun loadFavorites(): Single<List<LocalFavorite>>

    @Query("DELETE FROM favorites WHERE imgUrl = :imgUrl")
    fun removeFavorite(imgUrl: String): Completable
}
