package tech.henridev.bored

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface RESTService {
    @GET("/api/activity/")
    suspend fun getActivity(@Query("participants") participants:String?, @Query("type") type:String? ): Response <ActivityData>
}