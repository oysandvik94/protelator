package com.protelator.ui

import kotlinx.html.*
import kotlinx.html.dom.*
import org.w3c.dom.Element

fun DIV.createIngredient() {
    form {
        id = "create-ingredient"
        attributes["hx-post"] = "/add-item"
        attributes["hx-target"] = "#create-ingredient"
        attributes["hx-swap"] = "outerHTML"
        classes = setOf("space-y-4")

        div(classes = "flex flex-wrap gap-2") {
            input(type = InputType.number, name = "protein") {
                classes = setOf("p-2 rounded bg-gray-800 text-white border border-gray-600 w-36")
                placeholder = "Protein (g)"
                required = true
                step = "0.1"
            }
            input(type = InputType.number, name = "grams") {
                classes = setOf("p-2 rounded bg-gray-800 text-white border border-gray-600 w-36")
                placeholder = "Total (g)"
                required = true
                step = "0.1"
            }
            button(type = ButtonType.submit) {
                classes = setOf("bg-sky-600 hover:bg-sky-700 focus:ring-2 focus:ring-sky-300 focus:outline-none text-white px-4 py-2 rounded transition font-semibold")
                +"Add Ingredient"
            }
        }
    }
}


