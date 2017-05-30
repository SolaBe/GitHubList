package com.sola.githublist.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sola.githublist.R;
import com.sola.githublist.models.UserListItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sola2Be on 29.05.2017.
 */

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int USER = 0;
    private static final int PROGRESS = 1;

    private List<UserListItem> userListItems;
    private Context context;
    private View.OnClickListener clickListener;
    private boolean isLoading;

    public UsersAdapter(Context context, View.OnClickListener listener) {
        this.context = context;
        this.clickListener = listener;
        userListItems = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;

        switch(viewType) {
            case USER :
                holder = new UsersViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.users_item, parent, false));
                break;
            case PROGRESS :
                holder = new ProgressViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.progress_item, parent, false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserListItem item = userListItems.get(position);

        switch (getItemViewType(position)) {
            case USER:
                UsersViewHolder uHolder = (UsersViewHolder) holder;
                Picasso.with(context).load(item.getAvatarUrl()).placeholder(R.drawable.github_logo)
                        .into(uHolder.avatar);
                uHolder.login.setText(item.getLogin());
                uHolder.type.setText(item.getType());
                if (item.isAdmin())
                    uHolder.admin.setVisibility(View.VISIBLE);
                else
                    uHolder.admin.setVisibility(View.GONE);

                holder.itemView.setTag(item.getLogin());
                holder.itemView.setOnClickListener(clickListener);
                break;
            case PROGRESS :
                break;
        }


    }

    @Override
    public int getItemCount() {
        return userListItems == null ? 0 : userListItems.size();
    }


    @Override
    public int getItemViewType(int position) {
        return (position == userListItems.size() - 1 && isLoading) ? PROGRESS : USER;
    }

    public void addItems(List<UserListItem> items) {
        int lastPos = userListItems.size() - 1;
        userListItems.addAll(items);
        notifyItemRangeInserted(lastPos, items.size());
    }

    public void showProgress() {
        isLoading = true;
    }

    public void hideProgress() {
        isLoading = false;
    }


    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView login;
        TextView type;
        TextView admin;

        public UsersViewHolder(View itemView) {
            super(itemView);

            avatar = (ImageView) itemView.findViewById(R.id.userAvatar);
            login = (TextView) itemView.findViewById(R.id.login);
            type = (TextView) itemView.findViewById(R.id.type);
            admin = (TextView) itemView.findViewById(R.id.isAdmin);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {

        public ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }



}
