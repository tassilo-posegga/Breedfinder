package eu.posegga.template.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteBreeds(
    val message: Map<String, List<String>>,
    val status: String
)
