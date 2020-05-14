package eu.posegga.template.domain.usecase

import eu.posegga.template.common.UseCase
import eu.posegga.template.data.repository.ItemRepository
import io.reactivex.Single

class LoadImagesUseCase(
    private val repository: ItemRepository
) : UseCase<LoadImagesUseCase.Params, Single<List<String>>> {

    override fun execute(params: Params): Single<List<String>> =
        params.subBreed?.let { repository.loadImagesForSubBreed(params.breed, params.subBreed) }
            ?: repository.loadImagesForBreed(params.breed)

    data class Params(
        val breed: String,
        val subBreed: String?
    )
}
