package com.week1.cashregisterpart1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ListView historyListView;

    private AlertDialog.Builder builder;

    PurchasedProduct selectedPurchasedProduct;
    String productName;
    double totalPrice;
    String purchaseDate;

    Button backBtn;
    private PurchasedProductAdapter purchasedProductAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Initialize history listview
        ListView historyListView = findViewById(R.id.history_list);

        // Initialize alertdialog builder
        builder = new AlertDialog.Builder(this);

        // Set history listview adapter
        Intent intent = getIntent();
        ArrayList<PurchasedProduct> purchasedProducts = (ArrayList<PurchasedProduct>) intent.getSerializableExtra("purchasedProducts");

        purchasedProductAdapter = new PurchasedProductAdapter(this, purchasedProducts);
        historyListView.setAdapter(purchasedProductAdapter);

        // Click purchased product in the list view
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get the selected purchased product
                selectedPurchasedProduct = purchasedProductAdapter.getItem(position);
                // Get the info for dialog box
                productName = selectedPurchasedProduct.getName();
                totalPrice = selectedPurchasedProduct.getPrice();
                purchaseDate = selectedPurchasedProduct.getTimestamp();

                // Set up alert dialog
                builder.setMessage("Product: " + productName + "\nPrice: " + totalPrice + "\nPurchase Date: " + purchaseDate)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        // Click back button
        backBtn = findViewById(R.id.history_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}