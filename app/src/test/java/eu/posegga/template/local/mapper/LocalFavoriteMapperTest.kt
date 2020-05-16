package eu.posegga.template.local.mapper

import eu.posegga.template.domain.model.Favorite
import eu.posegga.template.local.model.LocalFavorite
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class LocalFavoriteMapperTest {

    private val mapper = LocalFavoriteMapper()

    @Test
    fun from() {
        val result = mapper.to(local)
        with(result) {
            imgUrl shouldBe "imgUrl"
            displayableName shouldBe "displayableText"
        }
    }

    @Test
    fun to() {
        val result = mapper.from(domain)
        with(result) {
            imgUrl shouldBe "imgUrl"
            displayableText shouldBe "displayableName"
        }
    }

    private companion object {
        val local = LocalFavorite(
            imgUrl = "imgUrl",
            displayableText = "displayableText"
        )

        val domain = Favorite(
            imgUrl = "imgUrl",
            displayableName = "displayableName"
        )
    }
}
