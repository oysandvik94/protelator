package calculate
import kotlin.test.Test

class IngredientTest {
    @Test
    fun testIngredientCreation() {
        val ingredient = Ingredient(proteinPer100 = 20.0, mass = 30.0)

        assert(ingredient.totalProtein() == 6.0)
    }
}
