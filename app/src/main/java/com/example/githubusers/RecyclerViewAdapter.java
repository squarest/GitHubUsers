package com.example.githubusers;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubusers.databinding.RecyclerviewItemBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private List<User> users;

    public RecyclerViewAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        RecyclerviewItemBinding binding = RecyclerviewItemBinding.inflate(inflater, viewGroup, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        User user = users.get(i);
        viewHolder.setItem(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerviewItemBinding binding;

        public ViewHolder(View v) {
            super(v);
            this.binding = DataBindingUtil.bind(v);
        }

        public void setItem(User user) {
            binding.setUser(user);
        }
    }
}