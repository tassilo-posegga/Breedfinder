package eu.posegga.template.di

import eu.posegga.template.data.repository.BreedRepository
import eu.posegga.template.data.repository.FavoriteRepository
import eu.posegga.template.domain.usecase.AddFavoriteUseCase
import eu.posegga.template.domain.usecase.LoadImagesUseCase
import eu.posegga.template.domain.usecase.LoadItemsUseCase
import eu.posegga.template.domain.usecase.RemoveFavoriteUseCase
import eu.posegga.template.local.repository.FavoriteLocalSource
import eu.posegga.template.remote.api.ItemApi
import eu.posegga.template.remote.mapper.RemoteImagesMapper
import eu.posegga.template.remote.mapper.RemoteItemMapper
import eu.posegga.template.remote.provider.OkHttpProvider
import eu.posegga.template.remote.provider.RetrofitProvider
import eu.posegga.template.remote.repository.BreedRemoteSource
import eu.posegga.template.viewmodel.ImagesViewModel
import eu.posegga.template.viewmodel.ListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    single { OkHttpProvider(androidContext()).provide() }
    single { RetrofitProvider(get()).provide() }
    single { get<Retrofit>().create(ItemApi::class.java) }
    single { RemoteItemMapper() }
    single { RemoteImagesMapper() }
    single { BreedRemoteSource(get(), get(), get()) }
    single { FavoriteLocalSource() }
    single { BreedRepository(get()) }
    single { FavoriteRepository(get()) }
    single { LoadItemsUseCase(get()) }
    single { LoadImagesUseCase(get(), get()) }
    single { AddFavoriteUseCase(get()) }
    single { RemoveFavoriteUseCase(get()) }
    viewModel { ListViewModel(get()) }
    viewModel { ImagesViewModel(get(), get(), get()) }
}
