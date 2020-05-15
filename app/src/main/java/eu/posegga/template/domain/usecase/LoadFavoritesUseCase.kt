package eu.posegga.template.domain.usecase

import eu.posegga.template.common.NoArgsUseCase
import eu.posegga.template.data.repository.FavoriteRepository
import eu.posegga.template.domain.model.Favorite
import io.reactivex.Single

class LoadFavoritesUseCase(
    private val favoriteRepository: FavoriteRepository
) : NoArgsUseCase<Single<List<Favorite>>> {

    override fun execute(): Single<List<Favorite>> =
        favoriteRepository.loadFavorites()
}
