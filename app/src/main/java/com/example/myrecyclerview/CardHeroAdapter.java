package com.example.myrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardHeroAdapter extends RecyclerView.Adapter<CardHeroAdapter.CardHeroViewHolder> {
    private Context context;
    private ArrayList<Hero> heroes;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    @NonNull
    @Override
    public CardHeroViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_card_hero, viewGroup, false);
        return new CardHeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHeroViewHolder cardHeroViewHolder, final int i) {
        Hero hero = getHeroes().get(i);
        Glide.with(context)
                .load(hero.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(cardHeroViewHolder.imgPhoto);

        cardHeroViewHolder.tvName.setText(hero.getName());
        cardHeroViewHolder.tvFrom.setText(hero.getFrom());
        cardHeroViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Ini item favorite ku " + getHeroes().get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        cardHeroViewHolder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Favorite : "+getHeroes().get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        cardHeroViewHolder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Share : "+getHeroes().get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getHeroes().size();
    }

    public class CardHeroViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvFrom;
        Button btnFavorite, btnShare;
        public CardHeroViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvFrom = itemView.findViewById(R.id.tv_item_from);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
            btnShare = itemView.findViewById(R.id.btn_set_share);
        }
    }
}
