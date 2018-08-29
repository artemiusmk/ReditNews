package com.reddit.data.network

import com.reddit.data.RedditNewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface  RedditApi {

    @GET("/top.json")
    fun getTop(@Query("after") after: String,
               @Query("limit") limit: String): Observable<RedditNewsResponse>
}