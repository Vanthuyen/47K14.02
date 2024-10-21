package com.example.appstudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Product> mProducts;
    OnProductClickListener mListener;

    public ProductAdapter(Context context, ArrayList<Product> products, OnProductClickListener listener) {
        mContext = context;
        mProducts = products;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inf = LayoutInflater.from(mContext);
        View v = inf.inflate(R.layout.item_product, null);
        ImageView imgProduct = v.findViewById(R.id.img_product);
        TextView txtProductName = v.findViewById(R.id.txt_productname);
        TextView txtProductPrice = v.findViewById(R.id.txt_productprice);

        imgProduct.setImageResource(mProducts.get(i).getImage());
        txtProductName.setText(mProducts.get(i).getProductName());
        txtProductPrice.setText(mProducts.get(i).getProductPrice() + "");
        // if(i%2==0){
        // imgProduct.setImageResource(R.drawable.ic_launcher_background);
        // txtProductName.setText("Laptop"+i);
        // }else{
        // imgProduct.setImageResource(R.drawable.icon_test);
        // txtProductName.setText("Iphone"+i);
        v.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View view){
            mListener.onProductClick(mProducts.get(i).getProductName());
            }
        });

    return v;
    }
}
