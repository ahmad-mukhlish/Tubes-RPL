package com.geeksquad.android.tubes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geeksquad.android.tubes.R;
import com.geeksquad.android.tubes.entity.Personil;

import java.util.List;


public class AboutRecycleAdapter extends RecyclerView.Adapter<AboutRecycleAdapter.AboutViewHolder> {

    //TODO Personil should be square
    private final String LOG_TAG = AboutRecycleAdapter.class.getName();

    private Context mContext;
    private List<Personil> mPersonils;

    public AboutRecycleAdapter(Context mContext, List<Personil> mPersonils) {
        this.mContext = mContext;
        this.mPersonils = mPersonils;
    }

    @Override
    public AboutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.each_personil, parent, false);
        return new AboutViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(AboutViewHolder holder, int position) {

        Personil personilNow = mPersonils.get(position);

        holder.mAvatar.setImageResource(personilNow.getAvatar());
        holder.mName.setText(personilNow.getName());
        holder.mRole.setText(personilNow.getRole());

    }


    @Override
    public int getItemCount() {
        return mPersonils.size();
    }

    class AboutViewHolder extends RecyclerView.ViewHolder {

        ImageView mAvatar;
        TextView mName;
        TextView mRole;
        View mItemView;


        AboutViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.personil_name);
            mRole = itemView.findViewById(R.id.personil_role);
            mAvatar = itemView.findViewById(R.id.avatar);
            mItemView = itemView;
        }


    }


}