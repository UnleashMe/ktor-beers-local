package com.example.routes

import com.example.data.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private var snacks = mutableListOf(
    Snack(
        Snack.SnackData(
            UID = "c6a3e5ae-044d-4d9e-927b-a35774cbde67",
            name = "Омуль байкальский потр.",
            description = "Его вкус напоминает филе хариуса. Омуль обладает нежным мясом, которое его отличает от других представителей рода сиговых. Однажды попробовав его на вкус, вы сможете с легкостью определить, какую рыбу съели: омуль или подделку.",
            type = "Вяленая рыба",
            price = 250.0,
            weight = 1000.0
        )
    ),
    Snack(
        Snack.SnackData(
            UID = "61deb21a-2a1c-41e5-ab31-e0b133efddc8",
            name = "Скумбрия Премиум х/к",
            description = "Скумбрия обладает насыщенным сочным рыбным вкусом. Особенно хорошо он проявляется при холодном копчении.",
            type = "Рыба холодного копчения",
            price = 130.0,
            weight = 1000.0
        )
    ),
    Snack(
        Snack.SnackData(
            UID = "37b02eed-be1b-460b-8e50-f2528b7114ca",
            name = "Гренки контейнер пшеничные со вкусом красной икры ДОН КРУТОН",
            description = "Вкус изысканной красной икры влюбит в себя с первого хруста.",
            type = "Сухарики",
            price = 80.0,
            weight = 130.0
        )
    ),
    Snack(
        Snack.SnackData(
            UID = "36298af0-2ae4-4c48-9e22-2788a39b9112",
            name = "Кальмары копченые кольцами",
            description = "Ароматные подкопчённые колечки из тушки тихоокеанского кальмара – натуральный продукт, отличный деликатес и традиционная закуска к пиву.\\n\\nКальмар высушен естественным путем.\\n\\nЩадящая технология переработки позволяет сохранить природный вкус и цвет морепродукта, его питательные вещества.\\n\\nПо содержанию белка, витаминов В6, РР кальмары превосходят рыбу и мясо многих животных.\\n\\nПикантный вкус и соблазнительный аромат морепродукт приобретает в результате копчения на щепе фруктовых деревьев.",
            type = "Кальмары",
            price = 110.0,
            weight = 100.0
        )
    ),
    Snack(
        Snack.SnackData(
            UID = "f2be6981-49d2-4db1-a47f-1c68ac31bdec",
            name = "Пивчики",
            description = "Пивчики – это свиные сырокопченые тонкие колбаски со специями, чем-то напоминают джерки (куриные чипсы), но свинина дает более насыщенный вкус. Рецепт разработан специально как мясная закуска к пиву, поэтому такие колбаски хорошо сочетаются практически со всеми популярными сортами пенного напитка.",
            type = "Колбаса",
            price = 110.0,
            weight = 100.0
        )
    ),
    Snack(
        Snack.SnackData(
            UID = "389bd711-4dfd-4b60-96f9-2e48ea083ce6",
            name = "Лихачевские пельмени",
            description = "Для настоящих гурманов, в них добавлены абсолютно все ингридиенты",
            type = "Пельмени",
            price = 280.0,
            weight = 1000.0
        )
    )
)

fun Route.allSnacks() {
    get("/snacks") {
        call.respond(
            HttpStatusCode.OK, Snacks(snacks.map { it.data })
        )
    }
}


fun Route.getSnackById() {
    get("/snacks/{id}") {
        val id = call.parameters["id"]
        id?.let { _id ->
            val snack = snacks.find { it.data.UID == _id }
            snack?.let {
                call.respond(
                    HttpStatusCode.OK, it
                )
            } ?: call.respond(HttpStatusCode.BadRequest, OnSnackNotFoundResponse())
        }
    }
}

fun Route.addSnack() {
    post("/snacks/add-snack") {
        val snack = call.receive<Snack.SnackData>()
        if (snack.name in snacks.map { it.data.name }) {
            call.respond(HttpStatusCode.BadRequest, OnSnackExistsResponse(existingSnack = snack))
        }
        snacks.add(Snack(snack))
        call.respond(HttpStatusCode.Created, OnAddSnackResponse(createdSnack = snack))
    }
}

fun Route.deleteSnack() {
    delete("/snacks/{id}") {
        val id = call.parameters["id"]
        id?.let { _id ->
            val snack = snacks.find { it.data.UID == _id }
            snack?.let {
                snacks.remove(it)
                call.respond(HttpStatusCode.OK, OnDeleteSnackResponse(deletedSnack = it.data))
            } ?: call.respond(HttpStatusCode.BadRequest, OnSnackNotFoundResponse())
        }
    }
}

fun Route.updateSnack() {
    put("/snacks/{id}") {
        val id = call.parameters["id"]
        val snack = call.receive<Snack.SnackData>()
        id?.let { _id ->
            val sn = snacks.find { it.data.UID == id }
            sn?.let {
                snacks = snacks.map {
                    if (it.data.UID == _id) Snack(
                        Snack.SnackData(
                            UID = _id,
                            name = snack.name,
                            description = snack.description,
                            type = snack.type,
                            price = snack.price,
                            weight = snack.weight
                        )
                    ) else it
                }.toMutableList()
                call.respond(HttpStatusCode.OK, OnUpdateSnackResponse(updatedSnack = snack))
            } ?: call.respond(HttpStatusCode.BadRequest, OnSnackNotFoundResponse())
        }
    }
}
