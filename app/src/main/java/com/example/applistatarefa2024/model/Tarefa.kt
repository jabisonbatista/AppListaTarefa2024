package com.example.applistatarefa2024.model

import android.os.Parcelable
import java.io.Serializable


data class Tarefa(
    val idTarefa: Int?,
    val descricao: String,
    val dataCadastro: String
): Serializable
