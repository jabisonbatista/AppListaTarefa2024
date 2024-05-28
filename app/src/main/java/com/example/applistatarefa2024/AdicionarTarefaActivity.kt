package com.example.applistatarefa2024

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.applistatarefa2024.database.TarefaDAO
import com.example.applistatarefa2024.databinding.ActivityAdicionarTarefaBinding
import com.example.applistatarefa2024.model.Tarefa

class AdicionarTarefaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdicionarTarefaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            btnSalvar.setOnClickListener {

            }
            //checkBox()

        }


        //recuperar tarefa passada
        var tarefa: Tarefa? = null
        val bundle = intent.extras
        if(bundle != null){
           tarefa = bundle.getSerializable("tarefa") as Tarefa
            binding.editTarefa.setText(tarefa.descricao)

        }else{

        }

        binding.btnSalvar.setOnClickListener {

            if ( binding.editTarefa.text.isNotEmpty() ){

                if (tarefa != null){
                        editar(tarefa)
                }else{
                    salvar()
                    checkBox()
                }

            }else{
                Toast.makeText(
                    this,
                    "Preencha uma tarefa",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }

    private fun checkBox() {
        val selecionadoBaixo = binding.checkBaixo.isChecked
        binding.checkBaixo.text = "prioridade baixa $selecionadoBaixo"

    }

    private fun editar(tarefa: Tarefa) {
        val descricao = binding.editTarefa.text.toString()
        //val checkBox = binding.txtResultado.text.toString()
        val tarefaAtualizar = Tarefa(
            tarefa.idTarefa,
            descricao,
            "default")
        val tarefaDAO = TarefaDAO(this)

        if( tarefaDAO.atualizar(tarefaAtualizar)){
            Toast.makeText(
                this,
                "Tarefa atualizada com sucesso",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }

    }

    private fun salvar() {
        val descricao = binding.editTarefa.text.toString()
        //val checkBox = binding.txtResultado.text.toString()

        val tarefa = Tarefa(
            -1, descricao, "default"
        )


        val tarefaDAO = TarefaDAO(this)
        if (tarefaDAO.salvar(tarefa)) {
            Toast.makeText(
                this,
                "Tarefa cadastrada com sucesso",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

}