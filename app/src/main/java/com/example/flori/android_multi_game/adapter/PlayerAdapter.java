package com.example.flori.android_multi_game.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.flori.android_multi_game.MainActivity;
import com.example.flori.android_multi_game.R;
import com.example.flori.android_multi_game.ShowPlayerActivityRow;
import com.example.flori.android_multi_game.manager.PlayerManager;
import com.example.flori.android_multi_game.model.Player;
import com.example.flori.android_multi_game.utils.GameUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private ArrayList<Player> players;

    public PlayerAdapter(ArrayList<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.show_player_activity_row, viewGroup, false);
        return new PlayerAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Player player = players.get(position);
        viewHolder.firstName.setText(player.getFirstName());
        viewHolder.name.setText(player.getName());

        Picasso.get().load(player.getPicture()).into(viewHolder.image);


        viewHolder.playerRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerManager.getInstance().setPlayer(player);
                GameUtils.launchView((AppCompatActivity)v.getContext(), MainActivity.class, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView firstName;
        private LinearLayout playerRow;

        ViewHolder(View itemView) {

            super(itemView);
            image = itemView.findViewById(R.id.show_player_image);
            name = itemView.findViewById(R.id.show_player_name);
            firstName = itemView.findViewById(R.id.show_player_firstname);
            playerRow = itemView.findViewById(R.id.pick_player);

        }
    }
}