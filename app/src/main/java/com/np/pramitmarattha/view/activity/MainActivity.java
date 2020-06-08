package com.np.pramitmarattha.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.widget.EditText;
import com.np.pramitmarattha.R;
import com.np.pramitmarattha.view.fragment.LoginFragment;
import com.np.pramitmarattha.view.fragment.RegisterFragment;
import com.np.pramitmarattha.view.fragment.SplashFragment;
import com.np.pramitmarattha.view.fragment.TodoItemFormFragment;
import com.np.pramitmarattha.view.fragment.TodoListDetailFragment;
import com.np.pramitmarattha.view.fragment.TodoListsFragment;
import com.np.pramitmarattha.viewmodel.authHandleHomeViewModel;
import java.util.ArrayList;

/**
 * Base odf navigtion ====> MainActivity
 */

public class MainActivity extends AppCompatActivity implements
        SplashFragment.OnFragmentInteractionListener, LoginFragment.OnFragmentInteractionListener,
        RegisterFragment.OnFragmentInteractionListener, TodoListsFragment.OnFragmentInteractionListener,
        TodoListDetailFragment.OnFragmentInteractionListener, TodoItemFormFragment.OnFragmentInteractionListener,
        NavController.OnDestinationChangedListener {
    private Toolbar toolbar;
    private authHandleHomeViewModel viewModel;

    EditText EditingTheTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigation();
        viewModel = ViewModelProviders.of(this).get(authHandleHomeViewModel.class);
        viewModel.onAuthStatusChanged().observe(this, user -> {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            int fragment = user == null ? R.id.loginFragment : R.id.todoListsFragment;
            navController.navigate(fragment, null,
                    new NavOptions.Builder()
                            .setLaunchSingleTop(true)
                            .setPopUpTo(R.id.auth_navigation_graph, true)
                            .build()
            );
        });
    }


     // Setting up navigation with toolbar and top level fragments

    private void setupNavigation() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.splashFragment, R.id.loginFragment, R.id.todoListsFragment
        ).build();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navController.addOnDestinationChangedListener(this);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
    }
    @Override
    public void onFragmentInteraction(Uri uri) {
    }
    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

        // Toolbar hide garney ... when signin, signup, splash fragment ko bela
        if (destination.getId() == R.id.splashFragment || destination.getId() == R.id.loginFragment
                || destination.getId() == R.id.registerFragment) {
            toolbar.setVisibility(View.GONE);
        } else {
            toolbar.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
