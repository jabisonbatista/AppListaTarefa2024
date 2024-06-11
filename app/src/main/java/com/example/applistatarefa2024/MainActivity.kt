package com.example.applistatarefa2024



import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applistatarefa2024.adapter.TarefaAdapter
import com.example.applistatarefa2024.database.TarefaDAO
import com.example.applistatarefa2024.databinding.ActivityMainBinding
import com.example.applistatarefa2024.model.Tarefa

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var listaTarefas = emptyList<Tarefa>()
    private var tarefaAdapter: TarefaAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        binding.fabAdicionar.setOnClickListener {
            val intent = Intent(this, AdicionarTarefaActivity::class.java)
            startActivity( intent )
        }
        //Recyclerview
        tarefaAdapter = TarefaAdapter(
            {
                    id-> confirmarExclusao(id) },
            {
                    tarefa -> editar(tarefa)
            }
        )
        binding.rvTarefas.adapter = tarefaAdapter

        binding.rvTarefas.layoutManager = LinearLayoutManager(this)

    }
    private fun editar(tarefa: Tarefa) {
        val intent = Intent(this, AdicionarTarefaActivity::class.java )
        intent.putExtra("tarefa", tarefa)
        startActivity(intent)
    }

    private fun confirmarExclusao(id: Int) {

        val alertBuilder = android.app.AlertDialog.Builder(this)

        alertBuilder.setTitle("Confirmar exclusão")
        alertBuilder.setMessage("Deseja realmente excluir a tarefa?")

        alertBuilder.setPositiveButton("sim"){_,_ ->

            val tarefaDAO = TarefaDAO(this)
            if(tarefaDAO.removerPorId(id)){
                atualizarListaTarefas()
                Toast.makeText(this,"Sucesso ao remover tarefa", Toast.LENGTH_LONG).show()

            }else{

                Toast.makeText(this,"Erro ao remover tarefa", Toast.LENGTH_LONG).show()
            }
        }
        alertBuilder.setNegativeButton("não"){ _,_ ->
        }
        alertBuilder.create().show()
    }

    private fun atualizarListaTarefas(){

        val tarefaDAO = TarefaDAO(this)
        listaTarefas = tarefaDAO.listar()
        tarefaAdapter?.adicionarLista( listaTarefas )
    }
    override fun onStart() {
        super.onStart()
        atualizarListaTarefas()
    }
}