package com.example.marvel.characterDetail.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marvel.adapter.marvelDetailsAdapter;
import com.example.marvel.characterDetail.Presenter.CharacterListDetailsPresenter;
import com.example.marvel.models.Character;
import com.example.marvel.models.Item;
import com.task.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class charactersDetailsActivity extends AppCompatActivity implements characterDetailsView {

    Character character;

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.comicsRecyclerView)
    RecyclerView comicsRecyclerView;

    marvelDetailsAdapter comicsAdapter;
    marvelDetailsAdapter eventsAdapter;
    marvelDetailsAdapter seriesAdapter;
    marvelDetailsAdapter storiesAdapter;
    CharacterListDetailsPresenter presenter;

    List<Item> comicsArray = new ArrayList<>();
    List<Item> eventsArray = new ArrayList<>();
    List<Item> storiesArray = new ArrayList<>();
    List<Item> seriesArray = new ArrayList<>();
    @BindView(R.id.eventsRecyclerView)
    RecyclerView eventsRecyclerView;
    @BindView(R.id.seriesRecyclerView)
    RecyclerView seriesRecyclerView;
    @BindView(R.id.storiesRecyclerView)
    RecyclerView storiesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters_details);
        ButterKnife.bind(this);
        setValues();
        presenter = new CharacterListDetailsPresenter(this, character.getId());
        initComicList();
        initEventsList();
        initSeriesList();
        initStoriesList();

    }
    @OnClick(R.id.imageBack)
    public void backClick()
    {
        onBackPressed();
    }

    private void initComicList() {
        comicsAdapter = new marvelDetailsAdapter(this, presenter, comicsArray, "comics");
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        comicsRecyclerView.setLayoutManager(layoutManager);
        comicsRecyclerView.setHasFixedSize(true);
        comicsRecyclerView.setAdapter(comicsAdapter);
        presenter.getData(0, "comics");
    } // function of initComicList

    private void initEventsList() {
        eventsAdapter = new marvelDetailsAdapter(this, presenter, eventsArray, "events");
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        eventsRecyclerView.setLayoutManager(layoutManager);
        eventsRecyclerView.setHasFixedSize(true);
        eventsRecyclerView.setAdapter(eventsAdapter);
        presenter.getData(0, "events");
    } // function of initEventsList


    private void initSeriesList() {
        seriesAdapter = new marvelDetailsAdapter(this, presenter, eventsArray, "series");
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        seriesRecyclerView.setLayoutManager(layoutManager);
        seriesRecyclerView.setHasFixedSize(true);
        seriesRecyclerView.setAdapter(seriesAdapter);
        presenter.getData(0, "series");
    } // function of initSeriesList

    private void initStoriesList() {
        storiesAdapter = new marvelDetailsAdapter(this, presenter, eventsArray, "stories");
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        seriesRecyclerView.setLayoutManager(layoutManager);
        seriesRecyclerView.setHasFixedSize(true);
        seriesRecyclerView.setAdapter(storiesAdapter);
        presenter.getData(0, "stories");
    } // function of initStoriesList


    public void setValues() {

        character = (Character) getIntent().getExtras().getSerializable("characters");

        Glide.with(this)
                .load(character.getThumbnail().getPath() + "." + character.getThumbnail().getExtension())
                .placeholder(getDrawable(R.drawable.placeholder))
                .into(imageView);

        name.setText(character.getName());
        description.setText(character.getDescription());

    } // set data with values saved in serialaizable object from adapter


    @Override
    public void onDataDownloaded(List<Item> itemList, String type) {
        switch (type) {

            // fill array with comic from api
            case "comics":
                comicsArray.addAll(itemList);
                comicsAdapter.notifyDataSetChanged();
                break;

            // fill array with events from api
            case "events":
                eventsArray.addAll(itemList);
                eventsAdapter.notifyDataSetChanged();

                // fill array with series from api
            case "series":
                seriesArray.addAll(itemList);
                seriesAdapter.notifyDataSetChanged();

                // fill array with stories from api
            case "stories":
                storiesArray.addAll(itemList);
                storiesAdapter.notifyDataSetChanged();

        }
    }
}
