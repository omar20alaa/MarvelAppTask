package com.example.marvel.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marvel.characterDetail.Presenter.CharacterListDetailsPresenter;
import com.example.marvel.characterDetail.view.charactersDetailsActivity;
import com.example.marvel.charactersList.presenter.CharacterListPresenter;
import com.example.marvel.models.Character;
import com.example.marvel.models.Item;
import com.task.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class marvelDetailsAdapter extends RecyclerView.Adapter<marvelDetailsAdapter.MyViewHolder> {

    // variables

    Context context;
    List<Item> items;
    String type;
    CharacterListDetailsPresenter presenter;

    public marvelDetailsAdapter(Context context , CharacterListDetailsPresenter presenter, List<Item> items , String type) {
        this.context = context;
        this.items = items;
        this.presenter = presenter;
        this.type = type;
    } // constructor

    public class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.imageDetails)
        ImageView imageDetails;


        @BindView(R.id.nameDetails)
        TextView nameDetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    } // class of MyViewHolder

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.details_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    private static final String TAG = "marvelAdapter";

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        String url = items.get(i).getThumbnail().getPath() + "." + items.get(i).getThumbnail().getExtension();
        Log.i(TAG, "onBindViewHolder: " + url);
        Glide.with(context)
                .load(url)
                .placeholder(context.getDrawable(R.drawable.placeholder))
                .into(myViewHolder.imageDetails);


        myViewHolder.nameDetails.setText(items.get(i).getTitle());

        if (i == items.size() - 1) {
            presenter.getData(items.size() , type);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


} // class of HomeCategoryAdapter

