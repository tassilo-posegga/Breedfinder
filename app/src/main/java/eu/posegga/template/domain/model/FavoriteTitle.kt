package eu.posegga.template.domain.model

data class FavoriteTitle(
    val title: String
) : FavoriteListItem {

    override fun equals(other: Any?): Boolean =
        (other as? FavoriteTitle)?.let {
            this.title == other.title
        } ?: false

    override fun hashCode(): Int =
        title.hashCode()
}