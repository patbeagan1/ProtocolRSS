import dev.patbeagan.protocolrss.core.Rss
import org.junit.jupiter.api.Test

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
