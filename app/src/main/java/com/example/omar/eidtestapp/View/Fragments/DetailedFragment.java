package com.example.omar.eidtestapp.View.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omar.eidtestapp.R;

/**
 * Created by omar on 7/7/2017.
 */

public class DetailedFragment extends Fragment {
    String id, title, text;
    TextView titleTxtView;
    ImageView shareText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detailed_fragment, null, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        titleTxtView = (TextView) view.findViewById(R.id.text);
        shareText = (ImageView) view.findViewById(R.id.share);

        id = bundle.getString("ItemId");

        title = bundle.getString("title");

        text = bundle.getString("text");
        titleTxtView.setText(text);

        shareText.setOnClickListener(new View.OnClickListener()

                                     {
                                         @Override
                                         public void onClick(View view) {
                                             Intent sendIntent = new Intent();
                                             sendIntent.setAction(Intent.ACTION_SEND);
                                             sendIntent.putExtra(Intent.EXTRA_TEXT, text + " Happy feast, to more text please download ");
                                             sendIntent.setType("text/plain");
                                             startActivity(sendIntent);
                                         }
                                     }

        );
    }
}
