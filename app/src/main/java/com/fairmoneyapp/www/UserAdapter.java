package com.fairmoneyapp.www;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<Users.User> mList;
    private Context mContext;
    private final static int FADE_DURATION = 1000; //FADE_DURATION in milliseconds
    public UserAdapter(List<Users.User> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View menus = inflater.inflate(R.layout.list_item_layout, parent, false);
        return new UserAdapter.ViewHolder(menus);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users.User user = mList.get(position);
        Glide.with(mContext)
                .load(user.picture)
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.pics);
        holder.emailText.setVisibility(user.email == null?View.GONE:View.VISIBLE);
        holder.emailText.setText(user.email==null?"":user.email);
        String name = user.title+" "+user.firstName+" "+user.lastName;
        holder.nameText.setText(name);
        holder.layout.setOnClickListener(view -> {
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext,holder.pics,"profileImg");
            Bundle bundle = new Bundle();
            bundle.putString("picsUrl",user.picture);
            bundle.putString("id",user.id);
            Intent intent = new Intent(mContext,UserDetailsActivity.class);
            intent.putExtras(bundle);
            mContext.startActivity(intent,options.toBundle());
        });
       // setFadeAnimation(holder.layout);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout layout;
        public CircleImageView pics;
        public TextView nameText;
        public TextView emailText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            pics = itemView.findViewById(R.id.pics);
            nameText = itemView.findViewById(R.id.name);
            emailText = itemView.findViewById(R.id.email);
        }
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
}
