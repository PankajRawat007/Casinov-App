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

public class IosCasinoAdapter extends RecyclerView.Adapter<IosCasinoAdapter.ViewHolder> {
    private Context mcontext;
    private ArrayList<IosCasinoModel> mlist;
    private OnItemclickListener mlistener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);
        View view= layoutInflater.inflate(R.layout.ioscasinoslider,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        IosCasinoModel review = mlist.get(i);
        ImageView image = viewHolder.imageView;
        TextView text1, text2;
        text1 = viewHolder.textView1;
        text2 = viewHolder.textView2;

        Glide.with(mcontext)
                .load(review.getImage()) // Remote URL of image.
                .into(image);
        text1.setText(review.getPrice());

        text2.setText(review.getDiscount());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    IosCasinoAdapter(Context context, ArrayList<IosCasinoModel> list) {
        mcontext = context;
        mlist = list;


    }

    public interface OnItemclickListener {
        void onItemClick(int position);

    }
    public void setOnItemClickListener(OnItemclickListener listener){
        mlistener=listener;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView1, textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.androidimageViewReview3);

            textView1 = itemView.findViewById(R.id.pricetext);
            textView2 = itemView.findViewById(R.id.discounttext);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mlistener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mlistener.onItemClick(position);
                        }
                    }
                }
            });
        }


    }
}
