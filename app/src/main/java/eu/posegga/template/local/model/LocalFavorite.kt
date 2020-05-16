package eu.posegga.template.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class LocalFavorite(
    @PrimaryKey
    @ColumnInfo(name = "imgUrl")
    val imgUrl: String,
    @ColumnInfo(name = "displayableText")
    val displayableText: String
)
