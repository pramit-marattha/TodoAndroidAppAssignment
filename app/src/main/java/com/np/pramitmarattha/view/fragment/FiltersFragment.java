package com.np.pramitmarattha.view.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.np.pramitmarattha.R;
import com.np.pramitmarattha.databinding.FragmentFiltersBinding;
import com.np.pramitmarattha.view.OptionsSelectorView;
import com.np.pramitmarattha.viewmodel.TasksItemsViewModel;


// Toditems lai filter garney and ordering them
 //It Uses binding with a custom view


public class FiltersFragment extends Fragment {
    private TasksItemsViewModel viewModel;
    private FragmentFiltersBinding binding;
    public FiltersFragment() {   // This is an empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_filters, container, false);
        View view = binding.getRoot();
        binding.setHandler(new EventHandler());
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(TasksItemsViewModel.class);
        binding.statusSelector.setSelectedIndex(viewModel.getStatus());
    }
    public class EventHandler {
        public void todo_ko_priority(OptionsSelectorView selectorView, int selectedOptionIndex) {
            switch (selectorView.getId()) {
                case R.id.statusSelector:
                    viewModel.setStatus(selectedOptionIndex);
                    break;
            }
        }
    }
}
