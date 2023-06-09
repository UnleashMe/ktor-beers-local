package com.example.plugins

import com.example.routes.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    
    routing {

        allBeer()
        getBeerById()
        addBeverage()
        deleteBeverage()
        updateBeverage()

        allSnacks()
        getSnackById()
        addSnack()
        deleteSnack()
        updateSnack()
        // Static plugin. Try to access `/static/index.html`
        static {
            resources("static")
        }

    }
}
