package com.example.listatarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarDefaults.color
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listatarefas.ui.theme.ListaTarefasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaTarefasTheme {
                listaTarefas(modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding())
            }
        }
    }
}

data class Tarefa(
    val nome: String,
    val descricao: String,
    val concluida: Boolean = false
)

@Composable
fun listaTarefas(modifier: Modifier = Modifier){

    Column {
        var nome by remember { mutableStateOf("") }
        var descricao by remember { mutableStateOf("") }
        val tarefas = remember { mutableStateListOf<Tarefa>() }

        Text(text = "Lista de Tarefas", fontSize = 28.sp, modifier = Modifier
            .padding(top = 80.dp)
            .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color(0xFF466915))
        Spacer(modifier = Modifier.height(30.dp))

        Column (modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
            OutlinedTextField(
                value = nome,
                onValueChange = {nome = it},
                label = {Text("Digite o nome da tarefa:")}
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = descricao,
                onValueChange = {descricao = it},
                label = { Text("Digite a descrição da tarefa:")}
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (nome.isNotBlank() && descricao.isNotBlank()) {
                    tarefas.add(Tarefa(nome, descricao))
                    nome = ""
                    descricao = ""
                }
            }, modifier = Modifier, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF466915))) {
                Text(text = "Salvar")
            }
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn (verticalArrangement = Arrangement.spacedBy(16.dp)){
                itemsIndexed(tarefas){index, tarefa ->
                    Card (modifier = Modifier.size(300.dp, 130.dp)){
                        Column (modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(5.dp)){
                            Text(text = tarefa.nome)
                            Text(text = tarefa.descricao)
                            Checkbox(
                                checked = tarefa.concluida,
                                onCheckedChange = {marcado ->
                                    tarefas[index] = tarefa.copy(concluida = marcado)
                                }
                            )
                        }
                    }
                }
            }
        }
        
    }

}


