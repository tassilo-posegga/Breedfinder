package eu.posegga.template.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteImages(
    val message: List<String>
)
