package com.np.pramitmarattha.MainTasks;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.np.pramitmarattha.R;
import com.np.pramitmarattha.database.TaskEntry;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter < TaskAdapter.TaskViewHolder > {
    // date format
    private static final String DATE_FORMAT = "dd/MM/yyy";
    // making a Member variable to handle item clicks
    final private ItemClickListener CustItemClickListener;
    // Creating a Class variables for the List that holds task data and the Context
    private List < TaskEntry > TaskEntriesList;
    private Context CustomContext;
    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
    /**
     * Constructor for the TodoAdapter that initializes the Context.
     *
     * @param context  the current Context
     * @param listener the ItemClickListener
     */
    public TaskAdapter(Context context, ItemClickListener listener) {
        CustomContext = context;
        CustItemClickListener = listener;
    }
    public List < TaskEntry > getTasks() {
        return TaskEntriesList;
    }

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new TaskViewHolder that holds the view for each task
     */
    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(CustomContext)
                .inflate(R.layout.task_layout, parent, false);

        return new TaskViewHolder(view);
    }
    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder   The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        // Determine the values of the wanted data
        TaskEntry taskEntry = TaskEntriesList.get(position);
        String description = taskEntry.getDescription();
        int priority = taskEntry.getPriority();
        String updatedAt = taskEntry.getTaskDate();
        //Set values
        holder.taskDescriptionView.setText(description);
        holder.updatedAtView.setText(" " + updatedAt);
        // Programmatically set the text and color for the priority TextView
        String priorityString = "" + priority; // converts int to String
        holder.priorityView.setText(priorityString);
        GradientDrawable priorityCircle = (GradientDrawable) holder.priorityView.getBackground();
        // priority based background change
        int priorityColor = getPriorityColor(priority);
        priorityCircle.setColor(priorityColor);
    }
    private int getPriorityColor(int priority) {
        int priorityColor = 0;

        switch (priority) {
            case 1:
                priorityColor = ContextCompat.getColor(CustomContext, R.color.materialRed);
                break;
            case 2:
                priorityColor = ContextCompat.getColor(CustomContext, R.color.materialOrange);
                break;
            case 3:
                priorityColor = ContextCompat.getColor(CustomContext, R.color.materialYellow);
                break;
            default:
                break;
        }
        return priorityColor;
    }
    @Override
    public int getItemCount() {
        if (TaskEntriesList == null) {
            return 0;
        }
        return TaskEntriesList.size();
    }
    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setTodoList(List < TaskEntry > todos) {
        TaskEntriesList = todos;
        notifyDataSetChanged();
    }
    public List < TaskEntry > getTodos() {
        return TaskEntriesList;
    }
    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }
    // Inner class for creating ViewHolders
    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Class variables for the task description and priority TextViews
        TextView taskDescriptionView;
        TextView updatedAtView;
        TextView priorityView;
        /**
         * Constructor for the TaskViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public TaskViewHolder(View itemView) {
            super(itemView);
            taskDescriptionView = itemView.findViewById(R.id.taskDescription);
            updatedAtView = itemView.findViewById(R.id.taskUpdatedAt);
            priorityView = itemView.findViewById(R.id.priorityTextView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int elementId = TaskEntriesList.get(getAdapterPosition()).getId();
            CustItemClickListener.onItemClickListener(elementId);
        }
    }


}