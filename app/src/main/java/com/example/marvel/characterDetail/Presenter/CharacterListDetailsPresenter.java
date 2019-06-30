package com.example.marvel.characterDetail.Presenter;

import android.util.Log;

import com.example.marvel.characterDetail.view.characterDetailsView;
import com.example.marvel.charactersList.view.characterListView;
import com.example.marvel.networking.Http;
import com.example.marvel.networking.Response.ItemResponse;
import com.example.marvel.networking.Response.characterResponse;
import com.example.marvel.networking.clients.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterListDetailsPresenter {

    // variables

    characterDetailsView view;
    int limit = 20;
    int char_id ;


    public CharacterListDetailsPresenter(characterDetailsView view, int char_id) {
        this.view = view;
        this.char_id = char_id;
    } // consructor

    public void getData(int offset, String type) {

        Call<ItemResponse> call = null;

        switch (type) {

            // comics data
            case "comics" :
                call = Http.create(Client.class).getComics(char_id ,offset , limit);
                   break;

            // events data
            case "events" :
                call = Http.create(Client.class).getEvents(char_id , offset , limit  );;
                break;

            // series data
            case "series" :
                call = Http.create(Client.class).getSeries(char_id , offset , limit );;
                break;

            // stories data
            case "stories" :
                call = Http.create(Client.class).getStories(char_id , offset , limit );;
                break;


        }

        if (call != null)
        {
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {

                    // response success
                    view.onDataDownloaded(response.body().getData().getItems() , type);
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {

                }
            });

        }

    } // get data from api

}
