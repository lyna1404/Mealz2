package com.example.mealz.Retrofit


import com.example.mealz.Entity.*
import com.example.mealz.url
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface EndPointRest {
    @GET("/restaus/getall")
    suspend fun getAllRestaus(): Response<List<Restaurant>>
    @GET("/menus/{restaurantId}")
    suspend fun getMenus(@Path("restaurantId") restaurantId: Int): Response<List<Menu>>
    @GET("/menu/{menuId}")
    suspend fun getMenu(@Path("menuId") menuId: Int): Response<Menu>
    @POST("/login")
    suspend fun login(@Body requestBody: LoginRequest): Response<utilisateur>
    @POST("/signup")
    suspend fun signup(@Body requestBody: SignUpRequest): Response<utilisateur>


    companion object {
        @Volatile
        var endpoint: EndPointRest? = null
        fun createEndpoint(): EndPointRest {
            if(endpoint ==null) {
                synchronized(this) {
                    endpoint = Retrofit.Builder().baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(EndPointRest::class.java)
                }
            }
            return endpoint!!

        }


    }
}