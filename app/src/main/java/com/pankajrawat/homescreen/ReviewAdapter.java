package com.pankajrawat.homescreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private Context mcontext;
    private ArrayList<ModelReview> mlist;
    private OnItemclickListener mlistener;

    public interface OnItemclickListener{
        void onItemClick(int position);

    }
    public void setOnItemClickListener(OnItemclickListener listener){
        mlistener=listener;

    }

    ReviewAdapter(Context context, ArrayList<ModelReview> list){
        mcontext=context;
        mlist=list;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);
        View view= layoutInflater.inflate(R.layout.reviewslider,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ModelReview review=mlist.get(i);
        ImageView image=viewHolder.imageView;
//        RatingBar rating=viewHolder.ratingBar;
        ImageView image1=viewHolder.imagestar1;
        ImageView image2=viewHolder.imagestar2;
        ImageView image3=viewHolder.imagestar3;
        ImageView image4=viewHolder.imagestar4;
        ImageView image5=viewHolder.imagestar5;
        ImageView image6=viewHolder.imageemptystar1;
        ImageView image7=viewHolder.imageemptystar2;
        ImageView image8=viewHolder.imageemptystar3;
        ImageView image9=viewHolder.imageemptystar4;
        ImageView image10=viewHolder.imageemptystar5;
        TextView text1,text2;
        text1=viewHolder.textView1;
        text2=viewHolder.textView2;
        String stars=review.getStars();
        int s=Integer.parseInt(stars);
        if(s==1){
            image1.setVisibility(View.VISIBLE);
            image7.setVisibility(View.VISIBLE);
            image8.setVisibility(View.VISIBLE);
            image9.setVisibility(View.VISIBLE);
            image10.setVisibility(View.VISIBLE);
        }
        else  if(s==2){
            image1.setVisibility(View.VISIBLE);
            image2.setVisibility(View.VISIBLE);
            image8.setVisibility(View.VISIBLE);
            image9.setVisibility(View.VISIBLE);
            image10.setVisibility(View.VISIBLE);


        }
        else  if(s==3){
            image1.setVisibility(View.VISIBLE);
            image2.setVisibility(View.VISIBLE);
            image3.setVisibility(View.VISIBLE);
            image9.setVisibility(View.VISIBLE);
            image10.setVisibility(View.VISIBLE);
        }
        else  if(s==4){
            image1.setVisibility(View.VISIBLE);
            image2.setVisibility(View.VISIBLE);
            image3.setVisibility(View.VISIBLE);
            image4.setVisibility(View.VISIBLE);
            image10.setVisibility(View.VISIBLE);
        }
        else  {
            image1.setVisibility(View.VISIBLE);
            image2.setVisibility(View.VISIBLE);
            image3.setVisibility(View.VISIBLE);
            image4.setVisibility(View.VISIBLE);
            image5.setVisibility(View.VISIBLE);
        }

        Glide.with(mcontext)
                .load(review.getImage()) // Remote URL of image.
                .into(image);
        text1.setText(review.getName());

        text2.setText(review.getDescription());


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView,imagestar1,imagestar2,imagestar3,imagestar4,imagestar5,imageemptystar1,imageemptystar2,imageemptystar3,imageemptystar4,imageemptystar5;
        //        RatingBar ratingBar;
        TextView textView1,textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageViewReview1);
//            ratingBar=itemView.findViewById(R.id.simpleRatingBar);
            imagestar1=itemView.findViewById(R.id.star1);
            imagestar2=itemView.findViewById(R.id.star2);
            imagestar3=itemView.findViewById(R.id.star3);
            imagestar4=itemView.findViewById(R.id.star4);
            imagestar5=itemView.findViewById(R.id.star5);
            imageemptystar1=itemView.findViewById(R.id.emptystar1);
            imageemptystar2=itemView.findViewById(R.id.emptystar2);
            imageemptystar3=itemView.findViewById(R.id.emptystar3);
            imageemptystar4=itemView.findViewById(R.id.emptystar4);
            imageemptystar5=itemView.findViewById(R.id.emptystar5);
            textView1=itemView.findViewById(R.id.text1);
            textView2=itemView.findViewById(R.id.text212);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mlistener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mlistener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }



}
