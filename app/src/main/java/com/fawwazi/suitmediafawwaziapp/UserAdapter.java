package com.fawwazi.suitmediafawwaziapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.myViewHolder> {
    ArrayList<UserData> userDataArrayList;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public UserAdapter(ArrayList<UserData> userDataArrayList) {
        this.userDataArrayList = userDataArrayList;
    }

    @NonNull
    @Override
    public UserAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.items_user, parent, false);
        return new UserAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.myViewHolder holder, int position) {
        UserData user = userDataArrayList.get(position);

        holder.tvFirstname.setText(user.getFirst_name());
        holder.tvLastname.setText(user.getLast_name());
        holder.tvEmail.setText(user.getEmail());

        Glide.with(holder.itemView.getContext())
                .load(user.getAvatar())
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .into(holder.ivAvatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(user.getFirst_name() + " " + user.getLast_name());
            }
        });

    }

    @Override
    public int getItemCount() {
        return userDataArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView tvFirstname, tvLastname, tvEmail;
        CircleImageView ivAvatar;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFirstname = itemView.findViewById(R.id.tv_firstname);
            tvLastname = itemView.findViewById(R.id.tv_lastname);
            tvEmail = itemView.findViewById(R.id.tv_email);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(String name);
    }
}
