package com.example

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.util.pipeline.*
import kotlinx.coroutines.delay

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        val body: suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit = {
            delay( ( Math.random() *  500 ).toLong())
            call.respondText("<html><head></head><body><form><input type=\"hidden\" id=\"CSRFToken-wppb\" name=\"CSRFToken-wppb\" value=\"asdf1234\" /></form></body></html>", contentType = ContentType.Text.Html)
        }
        get("/", body)

        get("/*", body)

        get("/*/*", body)

        get("/*/*/*", body)

        post("/*", body)


        // Static feature. Try to access `/static/ktor_logo.svg`
        static("/static") {
            resources("static")
        }
    }
}

