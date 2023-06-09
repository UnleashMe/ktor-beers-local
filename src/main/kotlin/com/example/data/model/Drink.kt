package com.example.data.model

import com.example.data.PictureProvider
import kotlinx.serialization.Serializable
import java.sql.Date
import java.sql.Timestamp
import java.util.UUID

@Serializable
data class Drink(
    val data: BeerData
) {
    @Serializable
    data class BeerData(
        val UID: String = UUID.randomUUID().toString(),
        val name: String,
        val description: String,
        val type: String,
        val price: Double,
        val alcPercentage: Double = 0.0,
        val salePercentage: Double? = null,
        val imagePath: String = PictureProvider.beveragePics.random(),
        val category: String = "",
        val isAvaliable: Boolean? = null,
        val tags: String? = null,
        val volume: Double? = null,
        val createdAt: String = Date(Timestamp(System.currentTimeMillis()).time).toString(),
        val updatedAt: String = Date(Timestamp(System.currentTimeMillis()).time).toString()
    )
}

@Serializable
data class Drinks(
    val data: List<Drink.BeerData>
)

@Serializable
data class OnAddBeverageResponse(
    val msg: String = "Beverage was created",
    val createdBeverage: Drink.BeerData
)

@Serializable
data class OnBeverageExistsResponse(
    val error: String = "INCORRECT_PAYLOAD",
    val errorDescription: String = "Beverage with this name already exists",
    val existingBeverage: Drink.BeerData
)

@Serializable
data class OnDeleteBeverageResponse(
    val msg: String = "Beverage was successfully deleted",
    val deletedBeverage: Drink.BeerData
)

@Serializable
data class OnBeverageNotFoundResponse(
    val data: Drink.BeerData? = null,
    val error: String = "OBJECT_NOT_FOUND",
    val errorDescription: String = "Beverage with this UID doesn`t exist",
    val beverage: Drink? = null
)

@Serializable
data class OnUpdateBeverageResponse(
    val msg: String = "Beverage was successfully updated",
    val updatedBeverage: Drink.BeerData
)
