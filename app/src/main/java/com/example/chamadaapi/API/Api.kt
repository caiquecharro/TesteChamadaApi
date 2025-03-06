package com.example.chamadaapi.API

import com.example.chamadaapi.Models.RespostaApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// Interface : classe apenas para ser consultada nao mudada
interface Api {

      /*@GET: anotacao para falar qual tipo da chamada no caso a chamada get que apenas chama uma url
        itens entre chaves {nome} sao marcados para ser populado pela chamada

        nessa parte esta sendo definido o final da url do endpoint(ou API)*/
    @GET("{cep}/json/")
    //funcao do endereco pedindo o dado cep , o @Path é para definir a variavel que esta entre {nome}
    //no final tem Call<TipoDoRetorno> que é o model de resposta da api nesse caso a classe RespostaApi
    fun pegarEndereco(@Path(value = "cep", encoded = true) cep:String): Call<RespostaApi>

}