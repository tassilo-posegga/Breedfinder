package eu.posegga.template.di

import eu.posegga.template.data.repository.BreedRepository
import eu.posegga.template.data.repository.FavoriteRepository
import eu.posegga.template.domain.usecase.AddFavoriteUseCase
import eu.posegga.template.domain.usecase.LoadBreedsUseCase
import eu.posegga.template.domain.usecase.LoadFavoritesUseCase
import eu.posegga.template.domain.usecase.LoadImagesUseCase
import eu.posegga.template.domain.usecase.RemoveFavoriteUseCase
import eu.posegga.template.local.db.FavoritesDatabase
import eu.posegga.template.local.mapper.LocalFavoriteMapper
import eu.posegga.template.local.repository.FavoriteLocalSource
import eu.posegga.template.remote.api.DogApi
import eu.posegga.template.remote.mapper.RemoteBreedMapper
import eu.posegga.template.remote.mapper.RemoteImagesMapper
import eu.posegga.template.remote.provider.OkHttpProvider
import eu.posegga.template.remote.provider.RetrofitProvider
import eu.posegga.template.remote.repository.BreedRemoteSource
import eu.posegga.template.viewmodel.BreedsViewModel
import eu.posegga.template.viewmodel.FavoritesViewModel
import eu.posegga.template.viewmodel.ImagesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    single { OkHttpProvider(androidContext()).provide() }
    single { RetrofitProvider(get()).provide() }
    single { get<Retrofit>().create(DogApi::class.java) }
    single { RemoteBreedMapper() }
    single { RemoteImagesMapper() }
    single { BreedRemoteSource(get(), get(), get()) }
    single { FavoritesDatabase.getInstance(androidContext()).favoriteDao() }
    single { LocalFavoriteMapper() }
    single { FavoriteLocalSource(get(), get()) }
    single { BreedRepository(get()) }
    single { FavoriteRepository(get()) }
    single { LoadBreedsUseCase(get()) }
    single { LoadImagesUseCase(get(), get()) }
    single { LoadFavoritesUseCase(get()) }
    single { AddFavoriteUseCase(get()) }
    single { RemoveFavoriteUseCase(get()) }
    viewModel { BreedsViewModel(get()) }
    viewModel { ImagesViewModel(get(), get(), get()) }
    viewModel { FavoritesViewModel(get(), get()) }
}
