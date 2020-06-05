package com.np.pramitmarattha.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.np.pramitmarattha.R;
import com.np.pramitmarattha.databinding.TodolistRecyclerviewItemBinding;
import com.np.pramitmarattha.database.TodoList;
import com.np.pramitmarattha.interfaceprompt.TodoListsDiffCallback;
import java.util.ArrayList;

public class TasksListsAdapter extends RecyclerView.Adapter<TasksListsAdapter.TodoListViewHolder> {
    private OnItemClickListener itemClickListener;
    private ArrayList<TodoList> todoLists = new ArrayList<>();
    public ArrayList<TodoList> getTodoLists() {
        return todoLists;
    }
    @NonNull
    @Override
    public TodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TodolistRecyclerviewItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.todolist_recyclerview_item, parent, false);
        return new TodoListViewHolder(itemBinding);
    }
    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder holder, int position) {
        holder.itemBinding.setTodoList(todoLists.get(position));
    }
    @Override
    public int getItemCount() {
        return todoLists.size();
    }
    public void setTodoLists(ArrayList<TodoList> newTodoLists) {
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new TodoListsDiffCallback(todoLists,
                newTodoLists), false);
        todoLists = newTodoLists;
        result.dispatchUpdatesTo(this);
    }
    class TodoListViewHolder extends RecyclerView.ViewHolder {
        TodolistRecyclerviewItemBinding itemBinding;
        public TodoListViewHolder(@NonNull TodolistRecyclerviewItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            itemBinding.getRoot().setOnClickListener(v -> {
                if (itemClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION)
                    itemClickListener.onItemClicked(todoLists.get(getAdapterPosition()));
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClicked(TodoList todoList);
    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}