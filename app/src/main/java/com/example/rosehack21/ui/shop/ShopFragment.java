package com.example.rosehack21.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rosehack21.EditItem;
import com.example.rosehack21.ItemsAdapter;
import com.example.rosehack21.Plant;
import com.example.rosehack21.PlantsAdapter;
import com.example.rosehack21.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {
    List<Plant> plants;
    private ShopViewModel shopViewModel;
    public static final String KEY_ITEM_TEXT = "item_text";
    public static final String KEY_ITEM_POSITION = "item_position";
    public static final int BUY_TEXT_CODE = 30;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shopViewModel =
                new ViewModelProvider(this).get(ShopViewModel.class);
        View root = inflater.inflate(R.layout.fragment_shop, container, false);
        shopViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // getActivity().setContentView(R.layout.fragment_shop);
        RecyclerView rvPlants = view.findViewById(R.id.rv_plants);

        loadShop();

        ItemsAdapter.OnClickListener onClickListener = new ItemsAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                Log.d("MainActivity","Single click at position " + position);
                // create the new activity
                Intent i = new Intent(getContext(), EditItem.class);
                // pass the data being edited
                //i.putExtra(KEY_ITEM_TEXT, plants.get(position));
                i.putExtra(KEY_ITEM_POSITION, position);
                // display the activity
                startActivityForResult(i, BUY_TEXT_CODE);
            }
        };
    }

    private void loadShop() {
        plants = new ArrayList<Plant>(4);
        plants.add(new Plant("img_aloe","aloe vera","A succulent plant best known for the gel in its leaves. Prefers dry soil and vulnerable to overwatering. Aloe plants typically need to be watered about " +
                "every 2-3 weeks in the spring and summer and even more sparingly during the fall and winter"));
        plants.add(new Plant("img_succulent", "succulent","These plants store water in their leaves and thrive in dry conditions." +
                "They thrive in direct sunlight. Water the soil directly when it is dry in a container with proper drainage to prevent rotting of roots."));
    plants.add(new Plant("img_snake_plant","snake plant","These evergreen perennials love warm, dry weather. " +
            "They can tolerate a variety of light conditions, but are susceptible to overwatering."));
    plants.add(new Plant("img_orchid", "orchid","These delicate flowers can burn when put in direct sunlight, and much prefer sunlight through dappled leaves or indirectly. " +
            "A popular way of watering orchids is by placing two large ice cubes on the soil once a week."));
    }
}