package com.example.applistatarefa2024.model


import java.io.Serializable




data class Tarefa(
   val idTarefa: Int?,
   val descricao: String,
   val dataCadastro: String,
   val prioridade: String,
   val descricaoDaTarefa: String

): Serializable
