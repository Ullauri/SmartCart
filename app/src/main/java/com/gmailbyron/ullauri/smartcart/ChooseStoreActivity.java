package com.gmailbyron.ullauri.smartcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class ChooseStoreActivity extends AppCompatActivity {
    private final Store store = Store.INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gmailbyron.ullauri.smartcart.R.layout.activity_choose_store);
    }

    public void chooseStore(View view) {
        switch (view.getId()) {
            case com.gmailbyron.ullauri.smartcart.R.id.bestBuy:
                store.setStore("best buy");
                break;
            default:
                System.err.println("ERROR: STORE NOT FOUND");
                return;
        }

        Intent chooseMode = new Intent(this, ChooseModeActivity.class);
        startActivity(chooseMode);
    }

}
