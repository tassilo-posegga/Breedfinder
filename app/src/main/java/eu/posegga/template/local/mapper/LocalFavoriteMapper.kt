package eu.posegga.template.local.mapper

import eu.posegga.template.common.BiDirectionalMapper
import eu.posegga.template.domain.model.Favorite
import eu.posegga.template.local.model.LocalFavorite

class LocalFavoriteMapper : BiDirectionalMapper<Favorite, LocalFavorite> {

    override fun from(input: Favorite): LocalFavorite = with(input) {
        LocalFavorite(
            imgUrl = imgUrl,
            displayableText = displayableName
        )
    }

    override fun to(input: LocalFavorite): Favorite = with(input) {
        Favorite(
            imgUrl = imgUrl,
            displayableName = displayableText
        )
    }
}
