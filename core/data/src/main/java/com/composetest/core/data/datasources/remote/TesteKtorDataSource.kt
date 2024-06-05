package com.composetest.core.data.datasources.remote

import android.health.connect.datatypes.units.Length
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TesteKtorDataSource @Inject constructor(
    private val httpClient: HttpClient
) {

    fun teste() = flow<Unit> {
        val r: Teste = httpClient.get("https://catfact.ninja/fact").body()
        val e = r
    }
}


@Serializable
data class Teste(
    val fact: String,
    val length: String
)