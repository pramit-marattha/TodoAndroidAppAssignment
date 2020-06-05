package com.np.pramitmarattha.interfaceprompt;

import androidx.recyclerview.widget.DiffUtil;
import com.np.pramitmarattha.database.TodoList;
import java.util.ArrayList;

/**
 * For updating the list when there is certain change detected
 *
 * Takes naya to purano todolist
 */
public class TodoListsDiffCallback extends DiffUtil.Callback {
    private ArrayList<TodoList> puranoList;
    private ArrayList<TodoList> nayaList;
    public TodoListsDiffCallback(ArrayList<TodoList> oldList, ArrayList<TodoList> newList) {
        this.puranoList = oldList;
        this.nayaList = newList;
    }
    @Override
    public int getOldListSize() {
        return puranoList == null ? 0 : puranoList.size();
    }
    @Override
    public int getNewListSize() {
        return nayaList == null ? 0 : nayaList.size();
    }
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return puranoList.get(oldItemPosition).getId() == nayaList.get(newItemPosition).getId();
    }
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return puranoList.get(oldItemPosition).equals(nayaList.get(newItemPosition));
    }
}