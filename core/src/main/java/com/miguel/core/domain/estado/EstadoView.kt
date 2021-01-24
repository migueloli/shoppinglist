package com.miguel.core.domain.estado

sealed class EstadoView {
    object Carregando: EstadoView()
    class Sucesso<T>(val modelo: T): EstadoView()
    class Erro(val erro: Throwable): EstadoView()
    class Aviso(val mensagem: String): EstadoView()
}
