package id.anggoroedo.gundam.retrofit

import id.anggoroedo.gundam.MainModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {

    @GET( "54c83dc5-3d03-417b-9e0d-83d9231fe981")
    fun getData(): Call<MainModel>

}