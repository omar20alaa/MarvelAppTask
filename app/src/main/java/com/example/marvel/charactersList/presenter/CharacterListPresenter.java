package com.example.marvel.charactersList.presenter;

import android.util.Log;

import com.example.marvel.charactersList.view.characterListView;
import com.example.marvel.networking.Http;
import com.example.marvel.networking.Response.characterResponse;
import com.example.marvel.networking.clients.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterListPresenter {

    // variables

    characterListView view;
    int limit = 20;
    String name = "";

    public void setName(String name) {
        this.name = name;
    } // set name to search

    public CharacterListPresenter(characterListView view) {
        this.view = view;
    }

    private static final String TAG = "CharacterListPresenter";

    public void getCharacters(int offset) {
        Call<characterResponse> call;
        if (name.length() != 0)
            call = Http.create(Client.class).getCharacters(offset, limit, name);
        else
            call = Http.create(Client.class).getCharacters(offset, limit);
        call.enqueue(new Callback<characterResponse>() {
            @Override
            public void onResponse(Call<characterResponse> call, Response<characterResponse> response) {
               // Log.i(TAG, "onResponse: " + response.body().getCode());

                view.onDataDownloaded(response.body().getData().getCharacters());
            }

            @Override
            public void onFailure(Call<characterResponse> call, Throwable t) {
                Log.i("Print", "fail");

            }
        });
    }
}
