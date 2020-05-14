package eu.posegga.template.remote.mapper

import android.annotation.SuppressLint
import eu.posegga.template.common.Mapper
import eu.posegga.template.domain.model.Breed
import eu.posegga.template.remote.model.RemoteBreeds

class RemoteItemMapper : Mapper<RemoteBreeds, List<Breed>> {

    @SuppressLint("DefaultLocale")
    override fun map(input: RemoteBreeds): List<Breed> =
        mutableListOf<Breed>().apply {
            input.message.entries.forEach { entrySet ->

                val breed = entrySet.key
                val subBreeds = entrySet.value

                if (subBreeds.isNotEmpty()) {
                    addAll(subBreeds.map {
                        Breed(
                            breed = breed,
                            subBreed = it,
                            displayableName = "${it.capitalize()} ${breed.capitalize()}"
                        )
                    })
                } else {
                    add(Breed(displayableName = breed.capitalize(), breed = breed))
                }
            }
        }
}