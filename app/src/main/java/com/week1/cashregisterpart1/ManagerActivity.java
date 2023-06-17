package com.week1.cashregisterpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {

    private Button historyBtn, restockBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        // Initialize btn and intent
        historyBtn = findViewById(R.id.button_history);
        restockBtn = findViewById(R.id.button_restock);
        backBtn =findViewById(R.id.button_back);

        // Click history button
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get intent from main activity
                Intent intent = getIntent();
                ArrayList<PurchasedProduct> purchasedProducts = (ArrayList<PurchasedProduct>) intent.getSerializableExtra("purchasedProducts");

                // Create new intent for history activity
                Intent toHistoryIntent = new Intent(ManagerActivity.this, HistoryActivity.class);
                toHistoryIntent.putExtra("purchasedProducts", purchasedProducts);
                startActivity(toHistoryIntent);
            }
        });

        // CLick restock button
        restockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new intent for restock activity
                Intent toRestockIntent = new Intent(ManagerActivity.this, RestockActivity.class);
                startActivity(toRestockIntent);
            }
        });

        // Click back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}