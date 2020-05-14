package eu.posegga.template.domain.usecase

import eu.posegga.template.common.NoArgsUseCase
import eu.posegga.template.data.repository.ItemRepository
import eu.posegga.template.domain.model.Breed
import io.reactivex.Single

class LoadItemsUseCase(
    private val repository: ItemRepository
) : NoArgsUseCase<Single<List<Breed>>> {

    override fun execute(): Single<List<Breed>> =
        repository.loadItems()
}