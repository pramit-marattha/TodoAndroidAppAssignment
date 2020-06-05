package com.np.pramitmarattha.interfaceprompt;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.np.pramitmarattha.R;

/**
 *Todolist add garna diyeko dialog haru
 *popup wala box haru
 */

public class AddNewTodoListDialog extends Dialog {
    private OnDismissListener listener;
    private String value = null;
    private EditText editText;
    public AddNewTodoListDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.addtododialog);
        if (getWindow() != null)
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        editText = findViewById(R.id.dialog_prompt_todo_item);
        Button buttonOk = findViewById(R.id.button_prompt_to_add);
        buttonOk.setOnClickListener(btn -> {
            value = editText.getText().toString();
            if (value.isEmpty()) {
                Toast.makeText(getContext(), getContext().getString(R.string.login_error_bata_auney_msg), Toast.LENGTH_SHORT).show();
                return;
            }
            dismiss();
        });
        setOnDismissListener(dialog -> {
            if (listener != null)
                listener.onDismiss(this, value);
        });
    }
    public void setListener(OnDismissListener listener) {
        this.listener = listener;
    }
    public interface OnDismissListener {
        void onDismiss(AddNewTodoListDialog dialog, String value);
    }
}
