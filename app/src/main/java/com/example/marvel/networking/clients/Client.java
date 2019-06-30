package com.example.marvel.networking.clients;

import com.example.marvel.networking.Response.ItemResponse;
import com.example.marvel.networking.Response.characterResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Client {

    @GET("characters")
    Call<characterResponse> getCharacters(@Query("offset") int offset, @Query("limit") int limit);
    // request for get character list

    @GET("characters")
    Call<characterResponse> getCharacters(@Query("offset") int offset, @Query("limit") int limit, @Query("nameStartsWith") String name);
    // request for get character list with query nameSrartswith for search filter

    @GET("characters/{character_id}/comics")
    Call<ItemResponse> getComics(@Path("character_id") int characterId, @Query("offset") int offset, @Query("limit") int limit);
    // request for get comics

    @GET("characters/{character_id}/events")
    Call<ItemResponse> getEvents(@Path("character_id") int characterId, @Query("offset") int offset, @Query("limit") int limit);
    // request for get events

    @GET("characters/{character_id}/series")
    Call<ItemResponse> getSeries(@Path("character_id") int characterId, @Query("offset") int offset, @Query("limit") int limit);
    // request for get series

    @GET("characters/{character_id}/stories")
    Call<ItemResponse> getStories(@Path("character_id") int characterId, @Query("offset") int offset, @Query("limit") int limit);
    // request for get stories

} // interface request