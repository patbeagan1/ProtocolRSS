import org.junit.jupiter.api.Test
import rss.Rss

class CreatingComplexObjectTest {

    @Test
    fun `test creating a complex object`() {
        Rss.create(
            "title",
            "link",
            "description"
        ) {
            this.language = "English"
        }
    }
}