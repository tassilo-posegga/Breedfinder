package eu.posegga.template.local.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import eu.posegga.template.assertSingleValueAndGet
import eu.posegga.template.domain.model.Favorite
import eu.posegga.template.local.db.FavoriteDao
import eu.posegga.template.local.mapper.LocalFavoriteMapper
import eu.posegga.template.local.model.LocalFavorite
import io.kotlintest.shouldBe
import io.reactivex.Single
import org.junit.jupiter.api.Test

internal class FavoriteLocalSourceTest {

    private val dao: FavoriteDao = mock()
    private val mapper: LocalFavoriteMapper = mock()

    private val local = FavoriteLocalSource(dao, mapper)

    @Test
    fun loadFavorites() {
        val favs = listOf(LocalFavorite("foo", "bar"))
        whenever(dao.loadFavorites()).thenReturn(Single.just(favs))
        whenever(mapper.to(any())).thenReturn(Favorite("baz", "bak"))

        val result = local.loadFavorites().test()
            .assertNoErrors()
            .assertComplete()
            .assertSingleValueAndGet()

        verify(dao).loadFavorites()
        verify(mapper).to(favs[0])

        with(result) {
            size shouldBe 1
            this[0].displayableName shouldBe "baz"
            this[0].imgUrl shouldBe "bak"
        }
    }

    @Test
    fun addFavorite() {
    }

    @Test
    fun removeFavorite() {
    }
}
