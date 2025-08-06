package com.protelator

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.html.*
import com.protelator.ui.createIngredient
import calculate.Ingredient
import ui.toElement

private val proteinEntries = mutableListOf<Ingredient>()

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondHtml {
                head {
                    title("Protelator")
                    script(src = "https://unpkg.com/htmx.org@2.0.3") {}
                    script(src = "https://cdn.tailwindcss.com") {}
                }
                body(classes = "bg-black text-white font-mono min-h-screen flex flex-col items-center justify-start p-6") {
                    div(classes = "w-full max-w-2xl space-y-8") {

                        div(classes = "border border-gray-700 bg-gray-900 rounded-lg p-6") {
                            h1(classes = "text-2xl font-semibold text-sky-400 tracking-wide mb-4 uppercase") {
                                +"Protein Calculator"
                            }
                            div(classes = "space-y-2") {
                                id = "ingredients"
                                createIngredient()
                            }
                        }

                        div(classes = "border border-gray-700 bg-gray-900 rounded-lg p-6") {
                            h2(classes = "text-xl font-semibold text-sky-300 mb-3") { +"Result" }
                            div() {
                                id = "result"
                                p(classes = "bg-green-100 text-green-800 font-semibold rounded px-4 py-2") {
                                    +"Total Protein: 0g"
                                }
                                p(classes = "text-sm text-gray-400 mt-1") {
                                    +"From 0 ingredient(s)"
                                }
                            }
                        }
                    }
                }

            }
        }

        post("/add-item") {
            val parameters = call.receiveParameters()
            val protein = parameters["protein"]?.toDoubleOrNull() ?: 0.0
            val grams = parameters["grams"]?.toDoubleOrNull() ?: 0.0

            val ingredient = Ingredient(proteinPer100 = protein, mass = grams)
            proteinEntries.add(ingredient)
            val totalProtein = proteinEntries.sumOf { it.totalProtein() }

            call.respondHtml {
                body {
                    ingredient.toElement()()

                    div {
                        createIngredient()
                    }

                    div {
                        id = "result"
                        attributes["hx-swap-oob"] = "innerHTML"
                        p(classes = "bg-green-100 text-green-800 font-semibold rounded px-4 py-2 text-base") {
                            +"Total Protein: ${totalProtein}g"
                        }
                        p(classes = "text-sm text-gray-400 mt-1") {
                            +"From ${proteinEntries.size} ingredient(s)"
                        }
                    }
                }
            }
        }

    }
}
