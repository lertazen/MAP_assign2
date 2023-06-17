package com.week1.cashregisterpart1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PurchasedProductAdapter extends ArrayAdapter<PurchasedProduct> {

    public PurchasedProductAdapter(Context context, List<PurchasedProduct> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the history list
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitem, parent, false);
        }

        // Get the current purchased product
        PurchasedProduct purchasedProduct = getItem(position);

        // Find the views and set corresponding data in the custom layout
        TextView productNameTextView = convertView.findViewById(R.id.product_name);
        TextView quantityTextView = convertView.findViewById(R.id.quantity);
        TextView priceTextView = convertView.findViewById(R.id.price);

        productNameTextView.setText(purchasedProduct.getName());
        quantityTextView.setText("Quantity: " + purchasedProduct.getQuantity());
        priceTextView.setText("Price: $" + purchasedProduct.getPrice());
        return convertView;
    }
}
