package com.example.data.model

import com.example.data.PictureProvider
import kotlinx.serialization.Serializable
import java.sql.Date
import java.sql.Timestamp
import java.util.UUID

@Serializable
data class Snack(
    val data: SnackData
) {
    @Serializable
    data class SnackData(
        val UID: String = UUID.randomUUID().toString(),
        val name: String,
        val description: String,
        val type: String,
        val price: Double,
        val weight: Double? = 100.0,
        val imagePath: String? = PictureProvider.snackPics.random(),
        val tags: String? = "",
        val createdAt: String = Date(Timestamp(System.currentTimeMillis()).time).toString(),
        val updatedAt: String = Date(Timestamp(System.currentTimeMillis()).time).toString()
    )
}

@Serializable
data class Snacks(
    val data: List<Snack.SnackData>
)

@Serializable
data class OnAddSnackResponse(
    val msg: String = "Snack was created",
    val createdSnack: Snack.SnackData
)

@Serializable
data class OnSnackExistsResponse(
    val error: String = "INCORRECT_PAYLOAD",
    val errorDescription: String = "Snack with this name already exists",
    val existingSnack: Snack.SnackData
)

@Serializable
data class OnSnackNotFoundResponse(
    val data: Snack.SnackData? = null,
    val error: String = "OBJECT_NOT_FOUND",
    val errorDescription: String = "Snack with this UID doesn`t exist",
    val snack: Snack? = null
)

@Serializable
data class OnDeleteSnackResponse(
    val msg: String = "Snack was successfully deleted",
    val deletedSnack: Snack.SnackData
)

@Serializable
data class OnUpdateSnackResponse(
    val msg: String = "Snack was successfully updated",
    val updatedSnack: Snack.SnackData
)