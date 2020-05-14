package eu.posegga.template.remote.mapper

import android.annotation.SuppressLint
import eu.posegga.template.common.Mapper
import eu.posegga.template.domain.model.Item
import eu.posegga.template.remote.model.UnsatisfyingJsonStructureModel

class RemoteItemMapper : Mapper<UnsatisfyingJsonStructureModel, List<Item>> {

    @SuppressLint("DefaultLocale")
    override fun map(input: UnsatisfyingJsonStructureModel): List<Item> =
        mutableListOf<Item>().apply {
            input.message.entries.forEach { entrySet ->
                if (entrySet.value.isNotEmpty()) {
                    addAll(entrySet.value.map { Item(it.capitalize()) })
                } else {
                    add(Item(entrySet.key.capitalize()))
                }
            }
        }
}