package eu.posegga.template.remote.mapper

import eu.posegga.template.common.Mapper
import eu.posegga.template.remote.model.RemoteImages

class RemoteImagesMapper : Mapper<RemoteImages, List<String>> {

    override fun map(input: RemoteImages): List<String> =
        input.message.map { it }
}
