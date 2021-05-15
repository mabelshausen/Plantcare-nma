package be.howest.marijnabelshausen.plantcare.network

import be.howest.marijnabelshausen.plantcare.domain.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://192.168.0.172:8000/api/"
//private const val BASE_URL = "http://192.168.10.10/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PlantCareApiService {
    @GET("rooms")
    fun getRooms():
            Call<List<Room>>
}

object PlantCareApi {
    val retrofitService : PlantCareApiService by lazy {
        retrofit.create(PlantCareApiService::class.java)
    }
}