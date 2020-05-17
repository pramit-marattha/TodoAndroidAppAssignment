package com.np.pramitmarattha.AddAndEditTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.np.pramitmarattha.R;
import com.np.pramitmarattha.MainTasks.RunnableExecutors;
import com.np.pramitmarattha.database.AppDatabase;
import com.np.pramitmarattha.database.TaskEntry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddEditTaskActivity extends AppCompatActivity {
    // to be received in the intent
    public static final String EXTRA_TASK_ID = "extraTaskId";
    // After Rotation
    public static final String INSTANCE_TASK_ID = "instanceTaskId";
    // Constants for priority
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 3;
    // The Constant for default task id should be used when not in update mode
    private static final int DEFAULT_TODO_ID = -1;
    private static final String TAG = AddEditTaskActivity.class.getSimpleName();
    // Fields
    EditText EditingTheTextDescription;
    RadioGroup GroupBoxRadioGroup;
    Button AddUpdateButton;
    EditText CustomDateSet;
    private int mTodoId = DEFAULT_TODO_ID;
    private TextView CustomDisplayingTheDate;
    private EditText SettingUpCustomDateText;
    private DatePickerDialog.OnDateSetListener CustomDateSetListener;
    // Member variable for the Database
    private AppDatabase ApplicationDataBase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
        CustomDisplayingTheDate = (TextView) findViewById(R.id.CustomDateSet);
        CustomDisplayingTheDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(AddEditTaskActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK, CustomDateSetListener, year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
                dialog.show();
            }
        });
        CustomDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                CustomDisplayingTheDate.setText(date);
            }
        };
        initViews();
        ApplicationDataBase = AppDatabase.getInstance(getApplicationContext());
        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            mTodoId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_TODO_ID);
        }
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            AddUpdateButton.setText(R.string.update_button);
            if (mTodoId == DEFAULT_TODO_ID) {
                // populate the UI
                mTodoId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TODO_ID);
                AddEditTaskViewModelFactoy factory = new AddEditTaskViewModelFactoy(ApplicationDataBase, mTodoId);
                final AddEditTaskViewModel viewModel = ViewModelProviders.of(this, factory).get(AddEditTaskViewModel.class);
                viewModel.getTasks().observe(AddEditTaskActivity.this, new Observer<TaskEntry>() {
                    @Override
                    public void onChanged(@Nullable TaskEntry taskEntry) {
                        viewModel.getTasks().removeObserver(this);
                        Log.d(TAG, "Yo yo check check receiving database update");
                        populateUI(taskEntry);
                    }
                });
            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_TASK_ID, mTodoId);
        super.onSaveInstanceState(outState);
    }
    /**
     * initViews is called from onCreate to init the member variable views
     */
    private void initViews() {
        EditingTheTextDescription = findViewById(R.id.editTextTaskDescription);
        SettingUpCustomDateText = findViewById(R.id.CustomDateSet);
        GroupBoxRadioGroup = findViewById(R.id.radioGroup);
        CustomDateSet = findViewById(R.id.CustomDateSet);
        AddUpdateButton = findViewById(R.id.saveButton);
        AddUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
    }
    /**
     * populateUI would be called to populate the UI when in update mode
     *
     * @param todo the todo to populate the UI
     */
    private void populateUI(TaskEntry todo) {
        if(todo == null)
            return;
        EditingTheTextDescription.setText( todo.getDescription());
        SettingUpCustomDateText.setText(todo.getTaskDate());
        setPriorityInViews(todo.getPriority());
    }
    /**
     * onSaveButtonClicked is called when the "save" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onSaveButtonClicked() {
        String description = EditingTheTextDescription.getText().toString();
        int priority = getPriorityFromViews();
        Date date = new Date();
        String task_date = CustomDateSet.getText().toString();

        // TODO (4) Make todo final so it is visible inside the run method
        final  TaskEntry pramitTodo = new TaskEntry(description, task_date, priority, date);
        RunnableExecutors.getInstance().DiskThread().execute(new Runnable() {
            @Override
            public void run() {
                if(mTodoId == DEFAULT_TODO_ID)
                    ApplicationDataBase.taskDao().insertTodo(pramitTodo);
                else{
                    pramitTodo.setId(mTodoId);
                    ApplicationDataBase.taskDao().update(pramitTodo);
                }
            }
        });
        // TODO (2) Get the diskIO Executor from the instance of AppExecutors and
        // call the diskIO execute method with a new Runnable and implement its run method
        // TODO (3) Move the remaining logic inside the run method

        finish();
    }

    /**
     * getPriority is called whenever the selected priority needs to be retrieved
     */
    public int getPriorityFromViews() {
        int priority = 1;
        int checkedId = ((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.radButton1:
                priority = PRIORITY_HIGH;
                break;
            case R.id.radButton2:
                priority = PRIORITY_MEDIUM;
                break;
            case R.id.radButton3:
                priority = PRIORITY_LOW;
        }
        return priority;
    }

    /**
     * setPriority is called when we receive a task from MainActivity
     *
     * @param priority the priority value
     */

    public void setPriorityInViews(int priority) {
        switch (priority) {
            case PRIORITY_HIGH:
                ((RadioGroup) findViewById(R.id.radioGroup)).check(R.id.radButton1);
                break;
            case PRIORITY_MEDIUM:
                ((RadioGroup) findViewById(R.id.radioGroup)).check(R.id.radButton2);
                break;
            case PRIORITY_LOW:
                ((RadioGroup) findViewById(R.id.radioGroup)).check(R.id.radButton3);
        }
    }


    public void getSpeechInput(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent,10);
        } else {
            Toast.makeText(this,"Sorry but the device doesn't support speech ",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if (resultCode==RESULT_OK && data != null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    EditingTheTextDescription.setText(result.get(0));
                }
                break;
        }
    }
}