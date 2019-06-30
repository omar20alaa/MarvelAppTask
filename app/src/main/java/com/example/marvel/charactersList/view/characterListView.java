package com.example.marvel.charactersList.view;

import com.example.marvel.models.Character;

import java.util.List;

public interface characterListView {
    void onDataDownloaded(List<Character> marvelList);
}
