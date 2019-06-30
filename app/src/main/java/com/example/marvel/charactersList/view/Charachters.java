package com.example.marvel.charactersList.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvel.adapter.marvelAdapter;
import com.example.marvel.charactersList.presenter.CharacterListPresenter;
import com.example.marvel.models.Character;
import com.task.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Charachters extends AppCompatActivity implements characterListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.loadingSpinner)
    ProgressBar loadingSpinner;
    @BindView(R.id.comicsRecyclerView)
    RecyclerView comicsRecyclerView;
    List<Character> CharacterList = new ArrayList<>();
    marvelAdapter adapter;
    CharacterListPresenter presenter;
    @BindView(R.id.editText)
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        ButterKnife.bind(this);
        presenter = new CharacterListPresenter(this);
        initHomeList();
        loadingSpinner.setVisibility(View.VISIBLE);
        presenter.getCharacters(0);
        filterSearch();
        // Get the Drawable custom_progressbar
}

    private void filterSearch() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CharacterList.clear();
                adapter.notifyDataSetChanged();
                presenter.setName(s.toString());
                presenter.getCharacters(0);
            }
        });
    } // search filter method

    private void initHomeList() {
        adapter = new marvelAdapter(CharacterList, presenter, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        comicsRecyclerView.setLayoutManager(layoutManager);
        comicsRecyclerView.setHasFixedSize(true);
        comicsRecyclerView.setAdapter(adapter);

    } // function of initHomeList

    private static final String TAG = "Charachters";

    @Override
    public void onDataDownloaded(List<Character> marvelList) {
        Log.i(TAG, "onDataDownloaded: " + marvelList.size());
        loadingSpinner.setVisibility(View.GONE);
        CharacterList.addAll(marvelList);
        adapter.notifyDataSetChanged();
    } // fill array with data
}
