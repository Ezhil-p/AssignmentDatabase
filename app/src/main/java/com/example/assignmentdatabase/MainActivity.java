package com.example.assignmentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button insert;
    Button delete;
    Button update;
    EditText fname;
    EditText price;
    EditText quantity;
    EditText ufname;
    EditText dfname;
    MyDatabase db;
    TextView showlist;
    TextView uquantity;
    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireUpListeners();
        db=new MyDatabase(this,1);
    }

    private void wireUpListeners() {
        insert=(Button)findViewById(R.id.insert);
        delete=(Button)findViewById(R.id.delete);
        update=(Button)findViewById(R.id.update);
        fname=(EditText)findViewById(R.id.fname);
        price=(EditText)findViewById(R.id.price);
        quantity=(EditText)findViewById(R.id.quantity);
        ufname=(EditText)findViewById(R.id.ufname);
        dfname=(EditText)findViewById(R.id.dfname);
        showlist=(TextView)findViewById(R.id.sfruits);
        uquantity=(EditText)findViewById(R.id.uquantity);
        show=(Button)findViewById(R.id.show_all);
        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        show.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.insert:insertData();
                                break;
            case R.id.delete:deleteData();
                                break;
            case R.id.update:updateData();
                                break;
            case R.id.show_all:showData();
                                break;
        }
    }

    private void showData() {
        ArrayList<Fruit> list=db.getValues();
        showlist.setText("");
        for(Fruit f:list)
            showlist.setText(showlist.getText().toString()+f+"\n");

    }

    private void updateData() {
        if(ufname.getText().toString().length()==0 || uquantity.getText().toString().length()==0)
            return;

        db.updateQuantity(ufname.getText().toString(),Integer.parseInt(uquantity.getText().toString()));
    }

    private void deleteData() {
        if(fname.getText().toString().length()==0)
            return;
        db.delete(fname.getText().toString());

    }

    private void insertData() {
        if(fname.getText().toString().length()==0 || price.getText().toString().length()==0 || quantity.getText().toString().length()==0)
            return;
        db.insert(fname.getText().toString(),Integer.parseInt(price.getText().toString()),Integer.parseInt(quantity.getText().toString()));
    }
}
