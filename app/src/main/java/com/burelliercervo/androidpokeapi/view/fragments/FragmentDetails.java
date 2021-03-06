package com.burelliercervo.androidpokeapi.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.manager.SPokemonManager;
import com.burelliercervo.androidpokeapi.model.Pokemon;

public class FragmentDetails extends BaseFragment {
    private int pokemonID;
//    public static FragmentDetails instanceFragment;
    private View v;
    private Pokemon pokemon;
    private ImageView imageViewSprite;
    private TextView textViewPokemonName;
    private TextView textViewXpData;
    private TextView textViewTypeData;
    private TextView textViewHeightData;
    private TextView textViewWeightData;
    private TextView textViewAttackData;
    private TextView tvAbilitiesData1;
    private TextView tvAbilitiesData2;
    private TextView tvAbilitiesData3;

//    public static FragmentDetails getInstanceFragment() {
//        if (instanceFragment == null) {
//            instanceFragment = new FragmentDetails();
//        }
//        return instanceFragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final SPokemonManager p = SPokemonManager.getInstance();
        v = inflater.inflate(R.layout.fragment_details_card, container, false);
        initViewDetails(v);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pokemonID = Integer.parseInt(bundle.getString("pokemonID"));
        }

        v = afficherDetails(pokemonID, v);

        return v;
    }

    public void initViewDetails(View v){
        imageViewSprite = (ImageView) v.findViewById(R.id.imageViewSprite);
        textViewPokemonName = (TextView) v.findViewById(R.id.tvPokemonName);
        textViewXpData = (TextView) v.findViewById(R.id.tvXpData);
        textViewTypeData = (TextView) v.findViewById(R.id.tvTypeData);
        textViewHeightData = (TextView) v.findViewById(R.id.tvHeightData);
        textViewWeightData = (TextView) v.findViewById(R.id.tvWeightData);
        textViewAttackData = (TextView) v.findViewById(R.id.tvAttackData);
        tvAbilitiesData1 = (TextView) v.findViewById(R.id.tvAbilitiesData1);
        tvAbilitiesData2 = (TextView) v.findViewById(R.id.tvAbilitiesData2);
        tvAbilitiesData3 = (TextView) v.findViewById(R.id.tvAbilitiesData3);
    }

    public void afficherPokemon(Pokemon pokemon) {
        Glide.with(this)
                .load(pokemon.getSprite())
                .into(imageViewSprite);
        textViewPokemonName.setText(pokemon.getName());
        textViewXpData.setText(pokemon.getXp());
        textViewTypeData.setText(pokemon.getType());
        textViewHeightData.setText(pokemon.getHeight());
        textViewWeightData.setText(pokemon.getWeight());
//        textViewAttackData.setText("Yolo");
        tvAbilitiesData1.setText("Manger");
        tvAbilitiesData2.setText("Courrir");
        tvAbilitiesData3.setText("Dormir");
    }

    private View afficherDetails(final int pokemonID, View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Pokemon pokemon = SPokemonManager.getInstance().getPokemonDetails(pokemonID);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        afficherPokemon(pokemon);
                    }
                });
            }
        }).start();
        return v;
    }
}
