package com.example.applistatarefa2024.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class  DatabaseHelper(context: Context): SQLiteOpenHelper(
    //1-contexto,
    // 2-nome do banco de dados
    // 3-cursorfactory,
    // 4-versao do banco de dados

    context, NOME_BANCO_DADOS,null, 1
) {

    companion object {
        const val NOME_BANCO_DADOS = "ListaTarefas.db"
        const val NOME_TABELA_TAREFAS = "tarefas"
        const val COLUNA_ID_TAREFA = "id_tarefa"
        const val COLUNA_DESCRICAO = "cadastro"
        const val COLUNA_DATA_CADASTRO = "data_cadastro"
        const val COLUNA_PRIORIDADE = "prioridade"
        const val COLUNA_DESCRICAODATAREFA = "descricao_tarefa"
    }

    //vamos criar as tabelas
    override fun onCreate(db: SQLiteDatabase?) {

        val sql = "CREATE TABLE IF NOT EXISTS $NOME_TABELA_TAREFAS (" +
                "$COLUNA_ID_TAREFA INTEGER not null PRIMARY KEY AUTOINCREMENT, " +
                "$COLUNA_DESCRICAO VARCHAR (80), " +
                "$COLUNA_PRIORIDADE TEXT, " +
                "$COLUNA_DATA_CADASTRO  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                "$COLUNA_DESCRICAODATAREFA TEXT " +
                ");"
        try {
            db?.execSQL(sql)
            Log.i("info_db", "Sucesso ao criar a tabela")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_db", "Erro ao criar a tabela")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}