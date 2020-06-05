package com.np.pramitmarattha.view.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.np.pramitmarattha.R;
import com.np.pramitmarattha.databinding.FragmentTodoListsBinding;
import com.np.pramitmarattha.database.TodoList;
import com.np.pramitmarattha.interfaceprompt.AddNewTodoListDialog;
import com.np.pramitmarattha.interfaceprompt.SwipeLeftDelete;
import com.np.pramitmarattha.interfaceprompt.YesNoDialog;
import com.np.pramitmarattha.adapter.TasksListsAdapter;
import com.np.pramitmarattha.viewmodel.TasksItemsViewModel;
import com.np.pramitmarattha.viewmodel.TasksListViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class TodoListsFragment extends Fragment implements YesNoDialog.OnDismissListener, TasksListsAdapter.OnItemClickListener {
    private static final int RC_WRITE_EXTERNAL_STORAGE = 1;
    private OnFragmentInteractionListener mListener;
    private TasksListViewModel viewModel;
    private FragmentTodoListsBinding fragmentTodoListsBinding;
    private TodoList todoList;

    public TodoListsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menumain, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            YesNoDialog yesNoDialog = new YesNoDialog(getContext());
            yesNoDialog.setMessage(getString(R.string.Bahira_Niskida_aauney_MESSAGE));
            yesNoDialog.show();
            yesNoDialog.setListener(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTodoListsBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_todo_lists, container, false);
        View view = fragmentTodoListsBinding.getRoot();
        fragmentTodoListsBinding.setClickHandler(new TodoListsFragmentEventHandler());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(TasksListViewModel.class);
        TasksListsAdapter todoListsAdapter = new TasksListsAdapter();
        fragmentTodoListsBinding.todoListRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentTodoListsBinding.todoListRecycleView.setHasFixedSize(true);
        todoListsAdapter.setItemClickListener(this);
        fragmentTodoListsBinding.todoListRecycleView.setAdapter(todoListsAdapter);

        if (getContext() != null)
            fragmentTodoListsBinding.todoListRecycleView.addItemDecoration(new DividerItemDecoration(getContext(),
                    DividerItemDecoration.VERTICAL));
        viewModel.onListUpdated().observe(getViewLifecycleOwner(), lists -> {
            todoListsAdapter.setTodoLists(((ArrayList<TodoList>) lists));
            fragmentTodoListsBinding.setNoDataVisibility(
                    lists.size() > 0 ? View.GONE : View.VISIBLE
            );
        });

//        onFileCreated();

        viewModel.onTodoListEmpty().observe(getViewLifecycleOwner(),
                aBoolean -> makeSnackBar(R.string.yo_todo_is_blank).show());

        viewModel.onTodoListTempDeleted().observe(getViewLifecycleOwner(), isTempDeleted -> {
            Snackbar.Callback onDismissed = new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    if (event != DISMISS_EVENT_ACTION) {
                        viewModel.deleteTodoList(todoList);
                    }
                }
            };
            View.OnClickListener onClickListener = v -> viewModel.tempDeleteTodoList(todoList, false);
            showUndoSnackBar(
                    R.string.TODOLIST_DELETED,
                    R.string.UNDO_DELETE,
                    onClickListener,
                    onDismissed
            );
        });
        enableSwipeToDelete(todoListsAdapter);
    }

    // shows a snackBar with undo delete button in case the user deleted by mistake
    private void showUndoSnackBar(int message, int action, View.OnClickListener actionClicked, Snackbar.Callback onDismissed) {
        Snackbar snackbar = makeSnackBar(message);
        snackbar.setAction(action, actionClicked);
        snackbar.addCallback(onDismissed);
        snackbar.show();
    }

    /**
     * makes a snackBar to be shown with a message
     *
     * @param message: the message to be shown
     * @return: returns the made snackBar
     */

    private Snackbar makeSnackBar(int message) {
        return Snackbar.make(fragmentTodoListsBinding.coordinatorLayout, message,
                Snackbar.LENGTH_LONG);
    }

    private void enableSwipeToDelete(TasksListsAdapter todoListsAdapter) {
        SwipeLeftDelete swipeToDeleteCallback = new SwipeLeftDelete(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                todoList = todoListsAdapter.getTodoLists().get(viewHolder.getAdapterPosition());
                viewModel.tempDeleteTodoList(todoList, true);
            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(fragmentTodoListsBinding.todoListRecycleView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDismiss(Dialog dialog, boolean onSureClicked) {
        if (onSureClicked) {
            viewModel.logout();
        }
    }

    @Override
    public void onItemClicked(TodoList todoList) {
        // when navigating from todolists to todoitems
        // we're going to clear the filters
        // because both TodoListDetailFragment and FiltersFragment use a shared viewmodel (activity scope).
        ViewModelProviders.of(getActivity()).get(TasksItemsViewModel.class).clearFilters();

        // Check if we're running on Android 5.0 or higher for animation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(fragmentTodoListsBinding.floatingAddActionListKo, getString(R.string.FAB_TRANSITION))
                    .build();
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).
                    navigate(TodoListsFragmentDirections.todoListToTodoDetail()
                            .setTodoListName(todoList.getName())
                            .setTodoListId(todoList.getId()), extras);
        } else {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).
                    navigate(TodoListsFragmentDirections.todoListToTodoDetail().setTodoListId(todoList.getId()));
        }


    }



    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


    /**
     * Event Handler for data binding
     */
    public class TodoListsFragmentEventHandler implements AddNewTodoListDialog.OnDismissListener {

        public void onAddFabClicked(View view) {
            AddNewTodoListDialog addNewTodoListDialog = new AddNewTodoListDialog(getContext());
            addNewTodoListDialog.show();
            addNewTodoListDialog.setListener(this);
        }

        @Override
        public void onDismiss(AddNewTodoListDialog dialog, String value) {
            if (value != null && !value.isEmpty()) {
                TodoList todoList = new TodoList();
                todoList.setName(value);
                viewModel.createTodoList(todoList);
            }
        }
    }
}
