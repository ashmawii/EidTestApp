package com.example.omar.eidtestapp.Controller.Adapter;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omar.eidtestapp.Model.ItemModel;
import com.example.omar.eidtestapp.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by omar on 7/7/2017.
 */

public class ItemAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    Fragment context;
    Fragment itemsAdapter;
    ArrayList<ItemModel> items = new ArrayList<>();
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        public void onItemClick(ItemModel item);

        public void onLongItemClick(ItemModel item);
    }


    public ItemAdapter(Fragment itemAdapter, OnItemClickListener listener) {
        mListener = listener;
        this.itemsAdapter = itemAdapter;
        this.context = new WeakReference<>(itemAdapter).get();
    }

    public void setData(ArrayList<ItemModel> data) {
        items.clear();
        if (data != null) {
            for (ItemModel itemInfo : data) {
                items.add(itemInfo);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        try {
            ItemModel itemInfo = getItem(position);
            viewHolder.name.setText(itemInfo.title);
        } catch (Exception exc) {
            Log.e("Error ",exc.toString());
        }

        viewHolder.bind(items.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ItemModel getItem(int position) {
        return items.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.title);
        }

        public void bind(final ItemModel item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
