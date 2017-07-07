package com.example.omar.eidtestapp.View.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.omar.eidtestapp.Controller.Adapter.ItemAdapter;
import com.example.omar.eidtestapp.Model.ItemModel;
import com.example.omar.eidtestapp.R;

import java.util.ArrayList;

/**
 * Created by omar on 7/6/2017.
 */

public class MainFragment extends Fragment implements ItemAdapter.OnItemClickListener {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    ItemAdapter adapter;
    ArrayList<ItemModel> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,null,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_photos);

        adapter = new ItemAdapter(this,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        for (int i = 0; i < 100; i++) {
            ItemModel item = new ItemModel();
            item.itemID = i;
            item.title = "This is Title for item: " + i;
            item.text = "This is Text for item: " + i;


            items.add(item);
        }

        adapter.setData(items);
        adapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.GONE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }, 1500);
    }

    @Override
    public void onItemClick(ItemModel item) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetailedFragment detailedItem = new DetailedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ItemId", item.itemID);
        bundle.putString("title", item.title);
        bundle.putString("text", item.text);
        detailedItem.setArguments(bundle);
        fragmentTransaction.replace(R.id.content_main, detailedItem);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onLongItemClick(ItemModel item) {

    }
}
