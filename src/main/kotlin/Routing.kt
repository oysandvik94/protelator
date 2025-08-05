package com.protelator

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.html.*
import com.protelator.ui.createIngredient

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondHtml {
                head {
                    title("Protelator")
                    script(src = "https://unpkg.com/htmx.org@2.0.3") {}
                    script(src = "https://cdn.tailwindcss.com") {}
                }
                body(classes = "font-sans max-w-4xl mx-auto p-5") {
                    h1 { +"Protein calculator" }

                    div(classes = "border border-gray-300 p-5 my-5 rounded-lg") {
                        h2 { +"Protein Calculator" }
                        div(classes = "mb-4") {
                            ul() {
                                id = "ingredients"
                                createIngredient()
                            }
                        }
                    }
                    
                    div(classes = "border border-gray-300 p-5 my-5 rounded-lg") {
                        h2 { +"Result" }
                        div() {
                            id = "result"
                            p(classes = "mt-2.5 p-2.5 bg-blue-50 rounded") { +"The result is here" }
                        }
                    }
                }
            }
        }

        post("/add-item") {
            val parameters = call.receiveParameters()
            val protein = parameters["protein"]?.toDoubleOrNull() ?: 0.0
            val grams = parameters["grams"]?.toDoubleOrNull() ?: 0.0

            call.respondHtml {
                body {
                    div(classes = "mt-2.5 p-2.5 bg-blue-50 rounded") {
                        p { +"Ingredient added: ${protein}g protein, ${grams}g total." }
                    }
                }
            }
        }
    }
}
