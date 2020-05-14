package eu.posegga.template.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Breed(
    val displayableName: String,
    val breed: String,
    val subBreed: String? = null
) : Parcelable {

    override fun toString(): String =
        displayableName
}