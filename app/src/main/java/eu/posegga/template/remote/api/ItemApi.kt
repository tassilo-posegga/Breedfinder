package eu.posegga.template.remote.api

import eu.posegga.template.remote.model.RemoteImages
import eu.posegga.template.remote.model.RemoteBreeds
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemApi {

    @GET("breeds/list/all")
    fun loadItems(): Single<RemoteBreeds>

    @GET("breed/{breed}/images")
    fun loadImagesForBreed(
        @Path("breed") breed: String
    ): Single<RemoteImages>

    @GET("breed/{breed}/{subBreed}/images")
    fun loadImagesForSubBreed(
        @Path("breed") breed: String,
        @Path("subBreed") subBreed: String
    ): Single<RemoteImages>
}
