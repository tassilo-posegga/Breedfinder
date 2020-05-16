package eu.posegga.template.remote.api

import eu.posegga.template.remote.model.RemoteBreeds
import eu.posegga.template.remote.model.RemoteImages
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {

    @GET("breeds/list/all")
    fun loadBreeds(): Single<RemoteBreeds>

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
