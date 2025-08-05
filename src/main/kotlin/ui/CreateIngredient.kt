package com.protelator.ui

import kotlinx.html.*
import kotlinx.html.dom.*
import org.w3c.dom.Element

fun UL.createIngredient() : Unit {
    return div( ) {
        id = "create-ingredient"
        form {
            attributes["hx-post"] = "/add-item"
            attributes["hx-target"] = "#create-ingredient"
            attributes["hx-swap"] = "beforebegin"
            input(type = InputType.number, name = "protein") {
                classes = setOf("p-2.5 m-1 border border-gray-300 rounded")
                placeholder = "Enter protein (grams)"
                required = true
                step = "0.1"
            }
            input(type = InputType.number, name = "grams") {
                classes = setOf("p-2.5 m-1 border border-gray-300 rounded")
                placeholder = "Enter total (grams)"
                required = true
                step = "0.1"
            }
            button(type = ButtonType.submit) {
                classes = setOf("bg-blue-600 hover:bg-blue-700 text-white px-4 py-2.5 m-1 border-none cursor-pointer rounded")
                +"Add ingredient"
            }
        }
    }
}
