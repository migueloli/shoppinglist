package com.miguel.core.domain.repository.resultado

sealed class ResultadoRepository {
    class Sucesso<T>(val modelo: T): ResultadoRepository()
    class Erro(val erro: Throwable): ResultadoRepository()
    class Aviso(val mensagem: String): ResultadoRepository()
}
