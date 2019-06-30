package com.example.marvel.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marvel.characterDetail.view.charactersDetailsActivity;
import com.example.marvel.charactersList.presenter.CharacterListPresenter;
import com.example.marvel.models.Character;
import com.task.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class marvelAdapter extends RecyclerView.Adapter<marvelAdapter.MyViewHolder> {


    // variables

    List<Character> marvelList;
    Context context;
    CharacterListPresenter presenter;

    public marvelAdapter(List<Character> marvelList, CharacterListPresenter presenter, Context context) {
        this.marvelList = marvelList;
        this.context = context;
        this.presenter = presenter;
    } // constructor

    public class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.item_image)
        ImageView item_image;


        @BindView(R.id.item_title)
        TextView item_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    } // class of MyViewHolder

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.marvel_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    private static final String TAG = "marvelAdapter";

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        String url = marvelList.get(i).getThumbnail().getPath() + "." + marvelList.get(i).getThumbnail().getExtension();
        Log.i(TAG, "onBindViewHolder: " + url);
        Glide.with(context)
                .load(url)
                .placeholder(context.getDrawable(R.drawable.placeholder))
                 .into(myViewHolder.item_image);

        myViewHolder.item_title.setText(marvelList.get(i).getName());


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, charactersDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("characters",marvelList.get(i));
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

        // intent object
        if (i == marvelList.size() - 1) {
            presenter.getCharacters(marvelList.size());
        }
    }

    @Override
    public int getItemCount() {
        return marvelList.size();
    }


} // class of HomeCategoryAdapter

