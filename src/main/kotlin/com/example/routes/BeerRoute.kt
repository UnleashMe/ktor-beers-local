package com.example.routes

import com.example.data.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private var beverages = mutableListOf(
    Drink(
        Drink.BeerData(
            UID = "b78619b4-6d8f-43d8-b688-efb1f16e4a7d",
            name = "Пина Колада",
            description = "Фруктовый сладкий сидр «Пина-Колада» — газированный пивной напиток с ярким экзотическим вкусом. Обладает насыщенным ароматом тропических фруктов — кокоса и ананаса.",
            type = "Разливное",
            price = 90.0,
            alcPercentage = 6.7,
            salePercentage = null
        )
    ),
    Drink(
        Drink.BeerData(
            UID = "5b03d1b1-11e5-4ed3-9e7f-b216aaa1d460",
            name = "Светлое",
            description = "В светлом пиве преобладает хмелевая горечь, сочетаемая с едва уловимым вкусом экстракта солода.",
            type = "Разливное",
            price = 150.0,
            alcPercentage = 7.0,
            salePercentage = null
        )
    ),
    Drink(
        Drink.BeerData(
            UID = "05c3a08c-071a-4af8-8c67-d148690e13bf",
            name = "Нефильтрованное",
            description = "Нефильтрованное пиво — не прошедшее процедуру пастеризации, то есть «живое».",
            type = "Светлое",
            price = 170.0,
            alcPercentage = 9.0,
            salePercentage = null
        )
    ),
    Drink(
        Drink.BeerData(
            UID = "929fbc5e-7d9e-4b09-bce8-073fd07db49e",
            name = "Kronenbourg 1664",
            description = "Kronenbourg 1664 Blanc — это легкое и освежающее пшеничное пиво с многослойным вкусовым профилем французского происхождения. Его уникальный вкус создает абсолютная гармония двух особых аспектов... Непринужденность и Элегантность!",
            type = "Бутылочное",
            price = 220.0,
            alcPercentage = 9.0,
            salePercentage = null
        )
    ),
    Drink(
        Drink.BeerData(
            UID = "17b7a5e7-b3ac-48db-8ff7-d0d16119dc22",
            name = "Кардымовское",
            description = "Легкое пиво",
            type = "Разливное",
            price = 110.0,
            alcPercentage = 8.0,
            salePercentage = null
        )
    )
)

fun Route.allBeer() {
    get("/beverages") {
        call.respond(
            HttpStatusCode.OK, Drinks(beverages.map { it.data })
        )
    }
}

fun Route.getBeerById() {
    get("/beverages/{id}") {
        val id = call.parameters["id"]
        id?.let { _id ->
            val beer = beverages.find { it.data.UID == _id }
            beer?.let {
                call.respond(
                    HttpStatusCode.OK, it
                )
            } ?: call.respond(HttpStatusCode.BadRequest, OnBeverageNotFoundResponse())
        }
    }
}

fun Route.addBeverage() {
    post("/beverages/add-beverage") {
        val beverage = call.receive<Drink.BeerData>()
        if (beverage.name in beverages.map { it.data.name }) {
            call.respond(HttpStatusCode.BadRequest, OnBeverageExistsResponse(existingBeverage = beverage))
        }
        beverages.add(Drink(beverage))
        call.respond(HttpStatusCode.Created, OnAddBeverageResponse(createdBeverage = beverage))
    }
}

fun Route.deleteBeverage() {
    delete("/beverages/{id}") {
        val id = call.parameters["id"]
        id?.let { _id ->
            val beer = beverages.find { it.data.UID == _id }
            beer?.let {
                beverages.remove(Drink(it.data))
                call.respond(HttpStatusCode.OK, OnDeleteBeverageResponse(deletedBeverage = it.data))
            } ?: call.respond(HttpStatusCode.BadRequest, OnBeverageNotFoundResponse())
        }
    }
}

fun Route.updateBeverage() {
    put("/beverages/{id}") {
        val id = call.parameters["id"]
        val beverage = call.receive<Drink.BeerData>()
        id?.let { _id ->
            val drink = beverages.find { it.data.UID == id }
            drink?.let {
                beverages = beverages.map {
                    if (it.data.UID == _id) Drink(
                        Drink.BeerData(
                            UID = _id,
                            name = beverage.name,
                            description = beverage.description,
                            type = beverage.type,
                            price = beverage.price,
                            alcPercentage = beverage.alcPercentage,
                            salePercentage = beverage.salePercentage
                        )
                    ) else it
                }.toMutableList()
                call.respond(HttpStatusCode.OK, OnUpdateBeverageResponse(updatedBeverage = beverage.copy(UID = id)))
            } ?: call.respond(HttpStatusCode.BadRequest, OnBeverageNotFoundResponse())
        }
    }
}