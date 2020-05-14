package eu.posegga.template.domain.usecase

import eu.posegga.template.common.UseCase
import eu.posegga.template.data.repository.FavoriteRepository
import io.reactivex.Completable

class RemoveFavoriteUseCase(
    private val repository: FavoriteRepository
) : UseCase<RemoveFavoriteUseCase.Params, Completable> {

    override fun execute(params: Params): Completable =
        repository.removeFavorite(params.imgUrl)

    data class Params(
        val imgUrl: String
    )
}
