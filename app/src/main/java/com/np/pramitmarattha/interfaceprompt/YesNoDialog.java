package com.np.pramitmarattha.interfaceprompt;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import com.np.pramitmarattha.R;
import com.np.pramitmarattha.databinding.DialogYesNoBinding;

/**
 * Custom Alert dialog box (when pressed sign out)
 *
 * consists of listeners onDismiss, onSure, onCancel to trigger action to views.
 */
public class YesNoDialog extends Dialog {
    private OnDismissListener listener;
    private boolean onSureClicked = false;
    private DialogYesNoBinding binding;
    public YesNoDialog(@NonNull Context context) {
        super(context);
        init();
    }
    private void init() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(
                getContext()), R.layout.dialog_yes_no, null, false);
        setContentView(binding.getRoot());
        binding.setClickHandler(new YesNoDialogEventHandler());
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        setOnDismissListener(dialog -> {
            if (listener != null)
                listener.onDismiss(this, onSureClicked);
        });
    }
    public void setMessage(String message) {
        binding.setWarningMessage(message);
    }
    public class YesNoDialogEventHandler {
        public void onSureBtnClicked(View view) {
            onSureClicked = true;
            Toast.makeText(getContext(), "Bye Bye..See ya ", Toast.LENGTH_SHORT).show();
            dismiss();
        }
        public void onCancelBtnClicked(View view) {
            onSureClicked = false;
            Toast.makeText(getContext(), "Noice !", Toast.LENGTH_SHORT).show();
            dismiss();
        }
    }
    public void setListener(OnDismissListener listener) {
        this.listener = listener;
    }
    public interface OnDismissListener {
        void onDismiss(Dialog dialog, boolean onSureClicked);
    }
}