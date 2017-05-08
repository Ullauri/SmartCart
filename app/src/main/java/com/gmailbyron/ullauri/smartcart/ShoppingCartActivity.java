package com.gmailbyron.ullauri.smartcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShoppingCartActivity extends AppCompatActivity {
    private final Store store = Store.INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.gmailbyron.ullauri.smartcart.R.layout.activity_shopping_cart);

        Intent chosenMode = getIntent();
        String mode = chosenMode.getStringExtra(getString(R.string.EXTRA_MODE));



    }
}
