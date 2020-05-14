package eu.posegga.template.domain.usecase

import eu.posegga.template.common.UseCase
import eu.posegga.template.data.repository.FavoriteRepository
import eu.posegga.template.domain.model.Favorite
import io.reactivex.Completable

class AddFavoriteUseCase(
    private val repository: FavoriteRepository
) : UseCase<Favorite, Completable> {

    override fun execute(params: Favorite): Completable =
        repository.addFavorite(params)
}
