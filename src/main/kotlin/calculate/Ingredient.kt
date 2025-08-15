package calculate

data class Ingredient(
    val proteinPer100: Double,
    val mass: Double,
    val kcalPer100: Double = 0.0
) {
    fun totalProtein(): Double = (proteinPer100 / 100) * mass
    fun totalKcal(): Double = (kcalPer100 / 100) * mass
}
