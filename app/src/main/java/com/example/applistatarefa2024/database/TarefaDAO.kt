package com.example.applistatarefa2024.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.applistatarefa2024.model.Tarefa
class TarefaDAO(context: Context) : ITarefaDAO {

    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(tarefa: Tarefa): Boolean {

        val conteudos = ContentValues()
        conteudos.put(DatabaseHelper.COLUNA_DESCRICAO, tarefa.descricao)
        conteudos.put(DatabaseHelper.COLUNA_PRIORIDADE, tarefa.prioridade)
        conteudos.put(DatabaseHelper.COLUNA_DESCRICAODATAREFA, tarefa.descricaoDaTarefa)

        try {
            escrita.insert(
                DatabaseHelper.NOME_TABELA_TAREFAS,
                null,
                conteudos
            )
            Log.i("info_db", "Sucesso ao salvar tarefa")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao salvar tarefa")
            return false
        }

        return true

    }
    override fun atualizar(tarefa: Tarefa): Boolean {
        val args = arrayOf(tarefa.idTarefa.toString())
        val conteudo = ContentValues()
        conteudo.put("${DatabaseHelper.COLUNA_DESCRICAO}, ${DatabaseHelper.COLUNA_PRIORIDADE}, ${DatabaseHelper.COLUNA_DESCRICAODATAREFA}", tarefa.descricao)

        try {
            escrita.update(
                DatabaseHelper.NOME_TABELA_TAREFAS,
                conteudo, "${DatabaseHelper.COLUNA_ID_TAREFA} = ?",
                args
            )
            Log.i("info_db", "Sucesso ao Atualizar")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao Atualizar")
            return false
        }

        return true
    }
    override fun removerPorId(idTarefa: Int): Boolean {
        val args = arrayOf(idTarefa.toString())
        try {
            escrita.delete(
                DatabaseHelper.NOME_TABELA_TAREFAS,
                "${DatabaseHelper.COLUNA_ID_TAREFA} = ?",
                args
            )
            Log.i("info_db", "Sucesso ao Remover")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao Remover")
            return false
        }
        return true

    }

    @SuppressLint("Recycle")
    override fun listar(): List<Tarefa> {

        val listaTarefas = mutableListOf<Tarefa>()

        val sql = """
    SELECT ${DatabaseHelper.COLUNA_ID_TAREFA},
           ${DatabaseHelper.COLUNA_DESCRICAO},
           strftime('%d/%m/%Y %H:%M', ${DatabaseHelper.COLUNA_DATA_CADASTRO}, '-3 hours') AS ${DatabaseHelper.COLUNA_DATA_CADASTRO},
           ${DatabaseHelper.COLUNA_PRIORIDADE},
           ${DatabaseHelper.COLUNA_DESCRICAODATAREFA}
    FROM ${DatabaseHelper.NOME_TABELA_TAREFAS}
"""
        val cursor = leitura.rawQuery(sql, null)

        val indiceId = cursor.getColumnIndex( DatabaseHelper.COLUNA_ID_TAREFA )
        val indiceDescricao = cursor.getColumnIndex( DatabaseHelper.COLUNA_DESCRICAO )
        val indiceData = cursor.getColumnIndex( DatabaseHelper.COLUNA_DATA_CADASTRO )
        val indicePrioridade = cursor.getColumnIndex( DatabaseHelper.COLUNA_PRIORIDADE )
        val indiceDescricaoTarefa = cursor.getColumnIndex( DatabaseHelper.COLUNA_DESCRICAODATAREFA )

        while ( cursor.moveToNext() ){

            val idTarefa = cursor.getInt( indiceId )
            val descricao = cursor.getString( indiceDescricao )
            val data = cursor.getString( indiceData )
            val prioridade = cursor.getString( indicePrioridade )
            val descricaoTarefa = cursor.getString( indiceDescricaoTarefa )

            listaTarefas.add(
                Tarefa(idTarefa, descricao, data, prioridade, descricaoTarefa)
            )

        }

        return listaTarefas

    }

}