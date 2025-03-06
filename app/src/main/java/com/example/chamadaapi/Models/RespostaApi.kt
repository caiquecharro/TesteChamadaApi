package com.example.chamadaapi.Models

import com.google.gson.annotations.SerializedName

//Model : modelo da resposta do servico para saber quais campos vao em quais lugares
data class RespostaApi(
    // serializedName : é uma anotacao que fala pro servico qual e o nome do campo original do servico
    @SerializedName("cep")
    //nome do campo e tipo dele
    var cep: String,

    @SerializedName("estado")
    var estado: String,

    @SerializedName("localidade")
    var cidade: String,

    @SerializedName("logradouro")
    var rua: String

)
