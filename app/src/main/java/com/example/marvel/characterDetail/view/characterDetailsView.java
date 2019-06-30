package com.example.marvel.characterDetail.view;

import com.example.marvel.models.Character;
import com.example.marvel.models.Item;

import java.util.List;

public interface characterDetailsView {

    void onDataDownloaded(List<Item> itemList , String type); // when data downloaded

} // character details interface
