package com.byrongmail.ullauri.smartcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class ChooseStore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_store);
    }


    public void chooseStore(View view) {
        switch (view.getId()) {
            case R.id.bestBuy:
                StoreFactory.createStore("best buy");
                break;
            default:
                System.err.println("ERROR: STORE NOT FOUND");
                return;
        }

        Intent chooseMode = new Intent(this, ChooseMode.class);
        startActivity(chooseMode);
    }

}
