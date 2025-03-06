package com.example.chamadaapi.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chamadaapi.API.Api
import com.example.chamadaapi.Models.RespostaApi
import com.example.chamadaapi.R
import com.example.chamadaapi.Utils.NetworkUtils
import retrofit2.Call
import retrofit2.Response

//classe e tipo
class MainActivity : AppCompatActivity() {

    //variavel global : sao variaveis que pode ser pega de qualquer lugar da classe
    lateinit var txtCep:EditText
    lateinit var lblEstado:TextView
    lateinit var txtCidade:EditText
    lateinit var btnBusca:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //colocando as referencias da tela nas variaveis globais
        txtCep = findViewById(R.id.txtCEP)
        lblEstado = findViewById(R.id.lblEstado)
        txtCidade = findViewById(R.id.txtCidade)
        btnBusca = findViewById(R.id.btnBuscar)

        //acao de clique do botao
        btnBusca.setOnClickListener {
            //chamando a funcao
            pegarEndereco()

        }

    }

    //funcao que serve para pegar o endereco pelo servico do via cep
    fun pegarEndereco(){

        //Instanciando as classes do retrofit e da api
        val retrofit = NetworkUtils.getRetrofitInstance("https://viacep.com.br/ws/")
        val api = retrofit.create(Api::class.java)

        /*
        Aqui é passando o cep pedido na classe Api , no object tem o metodo padrao e o
        tipo do retorno nesse caso RespostaApi.

        Quando criar o object: retrofit2.Callback<RespostaApi>{ pode clicar no direito no object
        e colocar para implementar os membros que é o sucesso e o erro.*/

        api.pegarEndereco("06626230").enqueue(object : retrofit2.Callback<RespostaApi>{
            //local aonde pega a resposta de servico e qual o modelo do retorno
            override fun onResponse(call: Call<RespostaApi>, response: Response<RespostaApi>) {

                // a resposta da chamada da api
                var resposta = response.body()

                // colocando os dados da resposta dentro dos campos
                txtCep.setText(resposta?.cep)
                lblEstado.text = resposta?.estado
                txtCidade.setText(resposta?.cidade)

                println(resposta)

            }

            //caso a chamada falhe passara por aqui
            override fun onFailure(call: Call<RespostaApi>, t: Throwable) {
                println("erro")
            }

        })


    }


}