package com.example.yunikim.callbackexam;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class ColorListFragment extends ListFragment {
    OnColorSelectedListener mListener;
    interface OnColorSelectedListener {
        void onColorSelected(int color);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> colorList = Arrays.asList("Red", "Green", "Blue");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, colorList);
        setListAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnColorSelectedListener)context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(((Activity)context).getLocalClassName() + " 는 OnColorSelectedListener를 구현해야 합니다.");
        }
    }

    public void setOnColorSelectedListener(OnColorSelectedListener listener) {
        mListener = listener;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ArrayAdapter<String> adapter = (ArrayAdapter<String>)l.getAdapter();
        String colorString = adapter.getItem(position);
        int color = Color.RED;
        switch(colorString) {
            case "Red":
                color = Color.RED;
                break;
            case "Green":
                color = Color.GREEN;
                break;
            case "Blue":
                color = Color.BLUE;
                break;
        }
        if(mListener != null)
            mListener.onColorSelected(color);
    }
}
