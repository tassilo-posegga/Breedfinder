package eu.posegga.template.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import eu.posegga.template.JunitRxSchedulerAndroidRule
import eu.posegga.template.domain.model.Breed
import eu.posegga.template.domain.usecase.LoadBreedsUseCase
import io.kotlintest.shouldBe
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor

internal class BreedsViewModelTest {

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    @get:Rule
    val rxRule = JunitRxSchedulerAndroidRule()

    private val loadBreedsUseCase: LoadBreedsUseCase = mock()
    private val observer: Observer<List<Breed>> = mock()

    private val viewModel = BreedsViewModel(loadBreedsUseCase)

    @Before
    fun setup() {
        viewModel.breedsLiveData.observeForever(observer)
    }

    @Test
    fun loadBreeds() {
        val breeds = listOf(Breed("foo", "bar"))
        whenever(loadBreedsUseCase.execute()).thenReturn(Single.just(breeds))

        viewModel.loadBreeds()

        verify(loadBreedsUseCase).execute()

        val captor = ArgumentCaptor.forClass(List::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture() as List<Breed>?)
            breeds shouldBe value
        }
    }
}
