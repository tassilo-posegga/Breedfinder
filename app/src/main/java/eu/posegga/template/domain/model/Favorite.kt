package eu.posegga.template.domain.model

data class Favorite(
    val displayableName: String,
    val imgUrl: String
) : FavoriteListItem {

    override fun identifier(): String =
        imgUrl

    override fun equals(other: Any?): Boolean =
        (other as? Favorite)?.let {
            this.displayableName == other.displayableName && this.imgUrl == other.imgUrl
        } ?: false

    override fun hashCode(): Int =
        31 * displayableName.hashCode() + imgUrl.hashCode()
}
