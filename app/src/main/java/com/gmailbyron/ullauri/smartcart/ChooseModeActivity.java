package com.gmailbyron.ullauri.smartcart;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ChooseModeActivity extends AppCompatActivity {
    private final Store store = Store.INSTANCE;
    private RadioGroup modeRadioGroup;
    private View[][] modeRadioGroupViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gmailbyron.ullauri.smartcart.R.layout.activity_choose_mode);

        TextView storeNameTextView = (TextView) findViewById(com.gmailbyron.ullauri.smartcart.R.id.storeName);
        storeNameTextView.setText(store.getName());

        modeRadioGroup = (RadioGroup) findViewById(com.gmailbyron.ullauri.smartcart.R.id.modeRadioGroup);

        modeRadioGroupViews = new View[][]{
                {findViewById(com.gmailbyron.ullauri.smartcart.R.id.defaultModeText)},
                {findViewById(com.gmailbyron.ullauri.smartcart.R.id.budgetModeText), findViewById(com.gmailbyron.ullauri.smartcart.R.id.budgetEditText)}
        };

        modeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                toggleViews();
            }
        });
    }

    private void toggleViews() {
        for (View[] modeRadioGroupView : modeRadioGroupViews) {
            for (View aModeRadioGroupView : modeRadioGroupView) {
                if (aModeRadioGroupView.getVisibility() == View.VISIBLE) {
                    aModeRadioGroupView.setVisibility(View.GONE);
                } else {
                    aModeRadioGroupView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void onClick(View v) {
        if (v.getId() == com.gmailbyron.ullauri.smartcart.R.id.backArrowButton)
            startActivity(new Intent(ChooseModeActivity.this, ChooseStoreActivity.class));

        Intent shoppingCart = new Intent(this, ShoppingCartActivity.class);
        switch (modeRadioGroup.getCheckedRadioButtonId()) {
            case com.gmailbyron.ullauri.smartcart.R.id.defaultModeRadio:
                shoppingCart.putExtra(getString(R.string.EXTRA_MODE), getString(R.string.mode_default));
                confirmDialog(this, "Confirm", "Store: " + store.getName() +
                        "\nMode: Default", "Start Shopping!", "Cancel", shoppingCart);
                break;

            case com.gmailbyron.ullauri.smartcart.R.id.budgetModeRadio:
                shoppingCart.putExtra(getString(R.string.EXTRA_MODE), getString(R.string.mode_budget));
                double budget = getBudget();
                shoppingCart.putExtra(getString(R.string.EXTRA_BUDGET), budget);
                confirmDialog(this, "Confirm", "Store: " + store.getName() +
                                "\nMode: Budget" + "\nBudget Amount: " + budget, "Start Shopping!",
                        "Cancel", shoppingCart);
                break;
        }
    }

    private double getBudget() {
        String budgetText = ((EditText) findViewById(com.gmailbyron.ullauri.smartcart.R.id.budgetEditText)).getText().toString();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
            budgetText = numberFormat.format(budgetText);
        } else {
            if (budgetText.contains(".")) {
                String afterDecimal = budgetText.substring(budgetText.indexOf(".") + 1);
                if (afterDecimal.length() == 0) {
                    budgetText += "00";
                } else if (afterDecimal.length() == 1) {
                    budgetText += "0";
                } else {
                    budgetText = budgetText.substring(0, budgetText.indexOf(".") + 2);
                }
            }
        }

        return Double.parseDouble(budgetText);
    }

    private AlertDialog confirmDialog(final Activity act, CharSequence title, CharSequence message,
                                      CharSequence buttonYes, CharSequence buttonNo, final Intent intent) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(intent);
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        return downloadDialog.show();
    }
}