package com.example.shop.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;


import com.example.shop.R;

import java.util.zip.Inflater;

public class DialogToBasket extends DialogFragment implements TextView.OnEditorActionListener {

    private EditText editTextQuantityToBasket;
    private Button buttonToBasket, buttonCancel;

    public DialogToBasket() {
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId){
            DialogToBasketListener listener = (DialogToBasketListener) getActivity();
            listener.onFinishEditDialog(editTextQuantityToBasket.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }

    public interface DialogToBasketListener {
        void onFinishEditDialog (String quantity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_to_basket, container);
        editTextQuantityToBasket = (EditText) view.findViewById(R.id.editTextQuantityToBasket);
        buttonToBasket = (Button) view.findViewById(R.id.buttonToBasket);
        buttonCancel = (Button) view.findViewById(R.id.buttonCancel);
        getDialog().setTitle(R.string.title_quantity);

        buttonToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
