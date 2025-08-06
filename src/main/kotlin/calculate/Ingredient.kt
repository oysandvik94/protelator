package calculate

data class Ingredient(
    val proteinPer100: Double,
    val mass: Double
) {
    fun totalProtein(): Double = (proteinPer100 / 100) * mass
}
