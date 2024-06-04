package com.example.applistatarefa2024.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.applistatarefa2024.R
import com.example.applistatarefa2024.databinding.ItemTarefaBinding

import com.example.applistatarefa2024.model.Tarefa

//O adapter é uma parte fundamental na criação de um app Android com Kotlin.
// Ele é responsável por fazer a ligação entre os dados que serão exibidos em uma lista ou em um RecyclerView
// e a interface do usuário
class TarefaAdapter(
    val onClickExcluir: (Int)-> Unit,
    val onClickEditar: (Tarefa)-> Unit


) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {
    private var listaTarefas: List<Tarefa> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun adicionarLista(lista: List<Tarefa> ){
        this.listaTarefas = lista
        notifyDataSetChanged()
    }

    inner class TarefaViewHolder(itemBinding: ItemTarefaBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        private val binding: ItemTarefaBinding

        init {
            binding = itemBinding
        }

     // Este código vincula um 'Tarefa' objeto
     // a alguns elementos da interface do usuário.
     @SuppressLint("ResourceType")
     fun bind(tarefa: Tarefa ){
            binding.textDescricao.text = tarefa.descricao

            binding.textData.text = tarefa.dataCadastro
         binding.btnExcluir.setOnClickListener{
             tarefa.idTarefa?.let { it1 -> onClickExcluir(it1) }
         }
         binding.btnEditar.setOnClickListener {
             onClickEditar(tarefa)
         }

            if(tarefa.prioridade == "baixa"){
                binding.textPrioridade.setBackgroundColor(Color.GREEN)
            }
            if(tarefa.prioridade == "media"){
                binding.textPrioridade.setBackgroundColor(Color.YELLOW)
            }
            if(tarefa.prioridade == "alta"){
                binding.textPrioridade.setBackgroundColor(Color.RED)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemTarefaBinding = ItemTarefaBinding.inflate(
            layoutInflater, parent, false
        )
        return TarefaViewHolder(itemTarefaBinding)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listaTarefas[position]//lista de objetos
        holder.bind( tarefa )
    }

    override fun getItemCount(): Int {
        return listaTarefas.size
    }

}

