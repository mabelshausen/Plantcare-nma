package be.howest.marijnabelshausen.plantcare.network

import be.howest.marijnabelshausen.plantcare.domain.Plant
import be.howest.marijnabelshausen.plantcare.domain.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

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
    suspend fun getRooms(): List<Room>

    @GET("rooms/{id}")
    suspend fun getRoomById(@Path("id") roomId: Int): Room

    @POST("rooms")
    suspend fun addRoom(@Body room: Room): Room

    @PUT("rooms/{id}")
    suspend fun editRoom(@Path("id") roomId: Int, @Body room: Room): Room

    @DELETE("rooms/{id}")
    suspend fun deleteRoom(@Path("id") roomId: Int)

    @GET("plants")
    suspend fun getPlants(): List<Plant>

    @GET("plants/{id}")
    suspend fun getPlantById(@Path("id") plantId: Int): Plant

    @POST("plants")
    suspend fun addPlant(@Body plant: Plant): Plant

    @PUT("plants/{id}")
    suspend fun editPlant(@Path("id") plantId: Int, @Body plant: Plant): Plant

    @DELETE("plants/{id}")
    suspend fun deletePlant(@Path("id") plantId: Int)
}

object PlantCareApi {
    val retrofitService : PlantCareApiService by lazy {
        retrofit.create(PlantCareApiService::class.java)
    }
}