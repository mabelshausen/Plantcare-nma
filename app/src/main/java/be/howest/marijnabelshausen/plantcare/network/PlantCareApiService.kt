package be.howest.marijnabelshausen.plantcare.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://192.168.0.172:8000/api/"
//private const val BASE_URL = "http://192.168.10.10/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface PlantCareApiService {
    @GET("rooms")
    fun getRooms():
            Call<String>
}

object PlantCareApi {
    val retrofitService : PlantCareApiService by lazy {
        retrofit.create(PlantCareApiService::class.java)
    }
}