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

public class BonusAdapter extends RecyclerView.Adapter<BonusAdapter.ViewHolder>  {
    private Context mcontext;
    private ArrayList<BonusModel> mlist;
    private OnItemclickListener mlistener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);
        View view= layoutInflater.inflate(R.layout.bonusslider,viewGroup,false);
        BonusAdapter.ViewHolder viewHolder=new BonusAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        BonusModel review = mlist.get(i);
        ImageView image = viewHolder.imageView;
        TextView text1, text2,text3;
        text1 = viewHolder.textView1;
        text2 = viewHolder.textView2;
        text3=viewHolder.textView3;

        Glide.with(mcontext)
                .load(review.getImage()) // Remote URL of image.
                .into(image);
        text1.setText(review.getPrice());

        text2.setText(review.getDiscount());
        text3.setText(review.getCasinoname());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

   BonusAdapter(Context context, ArrayList<BonusModel> list) {
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
        TextView textView1, textView2,textView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.bonusback);

            textView1 = itemView.findViewById(R.id.pricetext);
            textView2 = itemView.findViewById(R.id.discounttext);
            textView3=itemView.findViewById(R.id.bonuscasinoname);


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
