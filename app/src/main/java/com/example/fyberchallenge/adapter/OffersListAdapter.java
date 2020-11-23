package com.example.fyberchallenge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fyberchallenge.R;
import com.example.fyberchallenge.model.Offer;

import java.util.List;

public class OffersListAdapter extends RecyclerView.Adapter<OffersListAdapter.ViewHolder> {

    List<Offer> offersList;
    private LayoutInflater mInflater;
    private Context mContext;

    public OffersListAdapter(Context context, List<Offer> offers) {
        this.mInflater = LayoutInflater.from(context);
        this.offersList = offers;
        this.mContext = context;
    }

    @NonNull
    @Override
    public OffersListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_listview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersListAdapter.ViewHolder holder, int position) {
        Offer offer = offersList.get(position);
        holder.title.setText(offer.getTitle());
        Glide.with(mContext).load(offer.getThumbnail().getHires()).centerCrop().error(R.drawable.placeholder).into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView picture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            picture = itemView.findViewById(R.id.iv_picture);
        }
    }

    public void setDataAndNotify(List<Offer> arrayList) {
        offersList.clear();
        offersList.addAll(arrayList);
        notifyDataSetChanged();
    }
}