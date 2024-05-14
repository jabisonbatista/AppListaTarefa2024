package com.example.applistatarefa2024.database

import com.example.applistatarefa2024.model.Tarefa

interface ITarefaDAO {
    fun salvar(produto: Tarefa): Boolean
    fun atualizar(produto: Tarefa): Boolean
    fun remover(idproduto:Int): Boolean
    fun listar(): List<Tarefa>
}