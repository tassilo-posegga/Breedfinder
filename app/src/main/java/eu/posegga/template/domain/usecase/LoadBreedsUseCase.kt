package eu.posegga.template.domain.usecase

import eu.posegga.template.common.NoArgsUseCase
import eu.posegga.template.data.repository.BreedRepository
import eu.posegga.template.domain.model.Breed
import io.reactivex.Single

class LoadBreedsUseCase(
    private val repository: BreedRepository
) : NoArgsUseCase<Single<List<Breed>>> {

    override fun execute(): Single<List<Breed>> =
        repository.loadBreeds()
}
