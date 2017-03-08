package com.byrongmail.ullauri.smartcart;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ChooseMode extends AppCompatActivity {
    private String mode;
    private TextView storeNameTextView;
    private RadioGroup modeRadioGroup;
    private View[][] radioGroupTextViews;

    // pass bundle with chosen mode --- test in next activity for mode and get corresponding info i.e budget mode needs to get a budget

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);

        storeNameTextView = (TextView) findViewById(R.id.storeName);
        storeNameTextView.setText(StoreFactory.getName());

        radioGroupTextViews = new View[3][];
        radioGroupTextViews[0][0] = findViewById(R.id.defaultModeText);
        radioGroupTextViews[1][0] = findViewById(R.id.budgetModeText);
        radioGroupTextViews[1][1] = findViewById(R.id.budgetEditText);

        modeRadioGroup = (RadioGroup) findViewById(R.id.modeRadioGroup);

        modeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.defaultModeRadio:
                        toggleViews(0);
                        break;
                    case R.id.budgetModeRadio:
                        toggleViews(1);
                        break;
                    default:
                        System.err.println("Radio Button Not Found!");
                        break;
                }
            }
        });
    }

    private void toggleViews(int index) {
        for (int i = 0; i < radioGroupTextViews.length; i++) {
            if (i == index) {
                for (int k = 0; k < radioGroupTextViews[index].length; k++) {
                    radioGroupTextViews[index][k].setVisibility(View.VISIBLE);
                }
            } else {
                for (int k = 0; k < radioGroupTextViews[i].length; k++) {
                    radioGroupTextViews[i][k].setVisibility(View.GONE);
                }
            }
        }
    }

    private AlertDialog confirmDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent shoppingCart = new Intent(ChooseMode.this, ShoppingCart.class);
                Intent shoppingCart = new Intent(ChooseMode.this, ChooseStore.class);
                shoppingCart.putExtra("MODE", mode);
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
