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


        //recuperar tarefa passada
        var tarefa: Tarefa? = null
        val bundle = intent.extras
        if (bundle != null) {
            tarefa = bundle.getSerializable("tarefa") as Tarefa
            binding.editTarefa.setText(tarefa.descricao)

        }

        binding.btnSalvar.setOnClickListener {

            if (binding.editTarefa.text.isNotEmpty()) {

                if (tarefa != null) {
                    editar(tarefa)
                    //NivelPrioridade(tarefa)
                } else {
                    salvar()
                }

            } else {
                Toast.makeText(
                    this,
                    "Preencha uma tarefa",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }private fun editar(tarefa: Tarefa) {
        //Esta linha obtém o texto do campo de edição (editTarefa) e armazena na variável descricao.
        val descricao = binding.editTarefa.text.toString()
        val descricaoTarefa = binding.editDescricao.text.toString()
//Criando uma nova instância de Tarefa: idTarefa: O mesmo ID da tarefa original.
//descricao: A nova descrição obtida do campo de edição.
//"default": Um valor padrão (presumo que seja para algum campo que não está sendo editado neste momento).
//prioridade: A mesma prioridade da tarefa original.
        val tarefaAtualizar = Tarefa(
            tarefa.idTarefa, descricao, "default", tarefa.prioridade, descricaoTarefa
        )

        val tarefaDAO = TarefaDAO(this)

        if (tarefaDAO.atualizar(tarefaAtualizar)) {
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
            val descricaoTarefa = binding.editDescricao.text.toString()
            var prioridade = ""
            val checkBaixo = binding.checkBoxBaixo
            val checkMedio = binding.checkBoxMedio
            val checkAlto = binding.checkBoxAlto

            if(checkBaixo.isChecked){
                prioridade = "baixa"
                Toast.makeText(
                    this,
                    "Prioridade baixa selecionado",
                    Toast.LENGTH_SHORT
                ).show()

            }
            if(checkMedio.isChecked){
                prioridade = "Media"
                Toast.makeText(
                    this,
                    "Prioridade Média selecionado",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if(checkAlto.isChecked){
                prioridade = "alta"
                Toast.makeText(
                    this,
                    "Prioridade Alta selecionado",
                    Toast.LENGTH_SHORT
                ).show()
            }

            val tarefa = Tarefa(
                -1, descricao, "default", prioridade, descricaoTarefa
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
