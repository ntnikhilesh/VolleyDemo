package com.example.dell.volleydemo;

/**
 * Created by DELL on 12/7/2016.
 */

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Contact> arrayList;
    int position1;
    String all_tags,mtags="",profile_img="";
    private Context context;

//handle click event by interface
    private RecyclerViewClickListener listener;
    // Provide a suitable constructor (depends on the kind of dataset)
    //getting context and data from Main Activity
    public MyAdapter(ArrayList<Contact> arrayList){
        //this.context=applicationContext;
        this.arrayList = arrayList;
        //this.listener = listener;

    }



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_ques;
        public TextView tv_ques_rating;
        public TextView tv_ques_tags;
        public ImageView iv_profile_image;
        public Button button_like;
        public Button button_share;


        public MyViewHolder(View itemView, final RecyclerViewClickListener listener) {

            super(itemView);
            tv_ques = (TextView) itemView.findViewById(R.id.tv_ques);
            tv_ques_rating = (TextView) itemView.findViewById(R.id.tv_ques_rating);
            tv_ques_tags = (TextView) itemView.findViewById(R.id.tv_ques_tag);
            iv_profile_image=(ImageView)itemView.findViewById(R.id.iv_profile_img);
            button_like=(Button) itemView.findViewById(R.id.button_like);
            button_share=(Button) itemView.findViewById(R.id.button_share);


            // handle click event

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("row clicked","");
                    if(listener != null)
                        listener.onRowClicked(getAdapterPosition());
                }
            });

            tv_ques.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Ques","");
                    if(listener != null)
                        listener.onViewClicked(v, getAdapterPosition());
                }
            });

            button_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Share clicked","");
                    if(listener != null)
                        listener.onViewClicked(v, getAdapterPosition());
                }
            });

            button_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Share clicked","");
                    if(listener != null)
                        listener.onViewClicked(v, getAdapterPosition());
                }
            });

        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ques_row, parent, false);

        //set listner
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder myViewHolder = new MyViewHolder(v,listener);
        return myViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
           position1=position;
Log.d("nthere","-"+arrayList.get(position).getName());
            holder.tv_ques.setText(arrayList.get(position).getName());

      // Picasso.with(context).load(arrayList.get(position).getOwner().getProfile_image()).resize(120, 60).into(holder.iv_profile_image);

            holder.tv_ques_tags.setText(arrayList.get(position).getUser_name());
            holder.tv_ques_rating.setText(arrayList.get(position).getUser_pass());

    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}