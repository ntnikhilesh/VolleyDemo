package com.example.dell.volleydemo;

import android.view.View;

/**
 * Created by DELL on 12/8/2016.
 */

public interface RecyclerViewClickListener {

    void onRowClicked(int position);
    void onViewClicked(View v, int position);
}
