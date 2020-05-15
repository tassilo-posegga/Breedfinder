package eu.posegga.template.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eu.posegga.template.local.model.LocalFavorite

@Database(entities = [LocalFavorite::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        private const val DB_NAME = "Favorites.db"

        @Volatile
        private var INSTANCE: FavoritesDatabase? = null

        fun getInstance(context: Context): FavoritesDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): FavoritesDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                FavoritesDatabase::class.java,
                DB_NAME
            ).build()
    }
}
