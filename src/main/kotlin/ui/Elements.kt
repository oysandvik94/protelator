package ui

import kotlinx.html.*
import calculate.Ingredient

fun Ingredient.toElement(): FlowContent.() -> Unit = {
    div(classes = "bg-gray-800 text-gray-200 text-sm rounded px-4 py-2 mb-4") {
        p { +"Ingredient added: ${proteinPer100}g protein, ${mass}g total." }
    }
}
