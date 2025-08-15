package calculate
import kotlin.test.Test

class IngredientTest {
    @Test
    fun testIngredientCreation() {
        val ingredient = Ingredient(proteinPer100 = 20.0, mass = 30.0)

        assert(ingredient.totalProtein() == 6.0)
    }

    @Test
    fun testIngredientWithKcal() {
        val ingredient = Ingredient(proteinPer100 = 20.0, mass = 100.0, kcalPer100 = 200.0)

        assert(ingredient.totalProtein() == 20.0)
        assert(ingredient.totalKcal() == 200.0)
    }

    @Test
    fun testIngredientKcalCalculation() {
        val ingredient = Ingredient(proteinPer100 = 10.0, mass = 50.0, kcalPer100 = 300.0)

        assert(ingredient.totalKcal() == 150.0) // (300/100) * 50
    }
}
