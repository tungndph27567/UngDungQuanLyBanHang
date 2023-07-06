package com.example.foodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Activity.DetailTypeFoodActivity;
import com.example.foodapp.Model.TypeFood;
import com.example.foodapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TypeFoodAdapter extends RecyclerView.Adapter<TypeFoodAdapter.ViewHolder> {
    private Context mContext;
    private List<TypeFood> mList;

    public TypeFoodAdapter(Context mContext, List<TypeFood> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_san_pham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TypeFood typeFood = mList.get(position);
        if (typeFood == null) {
            return;
        }
        Glide.with(mContext).load(typeFood.getImg()).error(R.drawable.camera).into(holder.imgType);
        holder.tvNameType.setText(typeFood.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailTypeFoodActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("objTypeFood", typeFood);
                i.putExtras(bundle);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgType;
        private TextView tvNameType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgType = (CircleImageView) itemView.findViewById(R.id.img_Type);
            tvNameType = (TextView) itemView.findViewById(R.id.tv_nameType);
        }
    }
}
