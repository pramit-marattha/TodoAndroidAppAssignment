package com.np.pramitmarattha.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.np.pramitmarattha.R;
import com.np.pramitmarattha.databinding.FragmentRegisterBinding;
import com.np.pramitmarattha.database.EntireUserDatabase;
import com.np.pramitmarattha.viewmodel.SignUpViewModel;
import static com.basgeekball.awesomevalidation.ValidationStyle.UNDERLABEL;

public class RegisterFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private SignUpViewModel registerActivityViewModel;
    private EntireUserDatabase setUsers = new EntireUserDatabase();
    private AwesomeValidation mAwesomeValidation;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRegisterBinding fragmentRegisterBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_register, container, false);
        View view = fragmentRegisterBinding.getRoot();
        fragmentRegisterBinding.setClickHandler(new RegisterFragmentEventHandlers());
        fragmentRegisterBinding.setUser(setUsers);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFieldsValidation();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerActivityViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        registerActivityViewModel.onUserCreated().observe(getViewLifecycleOwner(),
                newlyCreatedUserId -> {
                    Toast.makeText(getContext(), getString(R.string.WELCOME), Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                            .navigate(RegisterFragmentDirections.registerToLogin());
                });
    }

    /**
     * Setting validation rules for field
     */
    private void initFieldsValidation() {
        mAwesomeValidation = new AwesomeValidation(UNDERLABEL);
        mAwesomeValidation.setContext(getContext());
        mAwesomeValidation.addValidation(getActivity(), R.id.fullNameSignup, RegexTemplate.NOT_EMPTY, R.string.login_error_bata_auney_msg);
        mAwesomeValidation.addValidation(getActivity(), R.id.userNameSignUp, RegexTemplate.NOT_EMPTY, R.string.login_error_bata_auney_msg);
        mAwesomeValidation.addValidation(getActivity(), R.id.userKoEmailSignUp, Patterns.EMAIL_ADDRESS, R.string.WRONG_FORMAT);
        mAwesomeValidation.addValidation(getActivity(), R.id.passwordRepeatConfirmation, R.id.userKoPasswordSignUp,
                R.string.PASSWORDS_DONT_MATCH);
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Event Handler class to bind events with views
     */
    public class RegisterFragmentEventHandlers {

        public void onRegisterButtonClicked(View view) {
            if (mAwesomeValidation.validate())
                registerActivityViewModel.createUser(setUsers);
        }

    }
}
