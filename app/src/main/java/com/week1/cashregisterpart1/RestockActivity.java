package com.week1.cashregisterpart1;

import static com.week1.cashregisterpart1.MainActivity.productList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RestockActivity extends AppCompatActivity {

    // Declare variables
    ListView restock_list;
    Button restock_ok_btn, back_btn;
    EditText enter_restock_edittext;
    TextView textViewName, textViewStockQty, textViewPrice;
    String restockInput;
    String name;
    int qty;
    double price;

    private AlertDialog.Builder builder;
    Product selectedProduct;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restock);

        // Initialize variables
        restock_list = findViewById(R.id.restock_listview);
        restock_ok_btn = findViewById(R.id.btn_restock_confirm);
        back_btn = findViewById(R.id.btn_restock_back);
        enter_restock_edittext = findViewById(R.id.restock_value);

        textViewName = findViewById(R.id.restock_productName);
        textViewStockQty = findViewById(R.id.restock_productQty);
        textViewPrice = findViewById(R.id.restock_productPrice);

        builder = new AlertDialog.Builder(this);

        adapter = new ProductAdapter(this, productList);
        restock_list.setAdapter(adapter);

        // Click on list view
        restock_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected product
                selectedProduct = adapter.getItem(position);
                // Populate the variables of product
                name = selectedProduct.getName();
                qty = selectedProduct.getQuantity();
                price = selectedProduct.getPrice();

                // Display on textview
                textViewName.setText(name);
                textViewStockQty.setText(String.valueOf(qty));
                textViewPrice.setText(String.valueOf(price));
            }
        });

        // Enter restock number
        enter_restock_edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Get user input
                    restockInput = enter_restock_edittext.getText().toString();

                    return true;
                }
                return false;
            }
        });

        // Click on OK button for restock
        restock_ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (enter_restock_edittext.getText().toString().equals("") || selectedProduct == null)
                    Toast.makeText(RestockActivity.this, "Please Select Product and Enter Restock Number", Toast.LENGTH_SHORT).show();

                else {
                    builder.setTitle("Confirm Purchase");
                    builder.setMessage("Do you want to confirm this stock?\nYour new stock is " + restockInput)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                // Action for Yes button
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Recalculate the new quantity for the selected product
                                    selectedProduct.setQuantity(qty + Integer.parseInt(restockInput));
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(RestockActivity.this, "Product restocked!", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                // Action for No button
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    enter_restock_edittext.setText("");
                                }
                            });
                    // Set up alert and show
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });

        // Click on back button
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}