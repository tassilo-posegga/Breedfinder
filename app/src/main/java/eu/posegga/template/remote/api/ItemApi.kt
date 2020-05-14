package eu.posegga.template.remote.api

import eu.posegga.template.remote.model.UnsatisfyingJsonStructureModel
import io.reactivex.Single
import retrofit2.http.GET

interface ItemApi {

    @GET("breeds/list/all")
    fun loadItems(): Single<UnsatisfyingJsonStructureModel>

}
