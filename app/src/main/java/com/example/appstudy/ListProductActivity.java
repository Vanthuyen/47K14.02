package com.example.appstudy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListProductActivity extends Activity implements OnProductClickListener{
    ListView lvProduct;
    ProductAdapter mAdapter;
    ArrayList<Product> listProducts=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview);

        setProducts();

        lvProduct = (ListView) findViewById(R.id.lv_products);
        mAdapter = new ProductAdapter(this, listProducts, this);
        lvProduct.setAdapter(mAdapter);
    }

    public void setProducts(){
        listProducts.add(new Product("Dell",200000000,R.drawable.ic_launcher_foreground));
        listProducts.add(new Product("HP",200000000,R.drawable.ic_launcher_background));
        listProducts.add(new Product("Macbook",200000000,R.drawable.ic_launcher_background));
        listProducts.add(new Product("Sony",200000000,R.drawable.ic_launcher_foreground));
        listProducts.add(new Product("LG gram",200000000,R.drawable.ic_launcher_background));
        listProducts.add(new Product("Asus",200000000,R.drawable.ic_launcher_foreground));
        listProducts.add(new Product("accer",200000000,R.drawable.ic_launcher_foreground));
        listProducts.add(new Product("Lenovo",200000000,R.drawable.ic_launcher_background));
        listProducts.add(new Product("Saphe",200000000,R.drawable.ic_launcher_background));
        listProducts.add(new Product("Dell",200000000,R.drawable.ic_launcher_foreground));
    }

    @Override
    public void onProductClick(String productName) {
        Toast.makeText(this,"Product "+productName, Toast.LENGTH_SHORT).show();;
    }
}
