package com.example.subham.banglasql;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity {

    private EditText E1,E2,E3,E4;
    private Button B1,B2,B3,B4,B5;
    TextView T1;
    mysqlite my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        E1=(EditText)findViewById(R.id.editText1);
        E2=(EditText)findViewById(R.id.editText2);
        E3=(EditText)findViewById(R.id.editText3);
        E4=(EditText)findViewById(R.id.editText4);
        B1=(Button)findViewById(R.id.button1);
        B2=(Button)findViewById(R.id.button2);
        B3=(Button)findViewById(R.id.button3);
        B4=(Button)findViewById(R.id.button4);
        B5=(Button)findViewById(R.id.button11);

        T1=(TextView) findViewById(R.id.textView);


        my= new mysqlite(this);


        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checker=my.addtotable(E1.getText().toString(),E2.getText().toString(),E3.getText().toString(),E4.getText().toString());


                if(checker==true) {
                    Toast.makeText(MainActivity.this,"Id= "+E1.getText()+"Name = " + E2.getText() + " Phone = " + E3.getText()+ " Email = " + E4.getText(), Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this,"NOT INSERTED / ID Cannot be same",Toast.LENGTH_LONG).show();
            }
        });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Cursor result =my.display();
                if (result.getCount()==0)
                {
                    Toast.makeText(MainActivity.this,"NO data Found",Toast.LENGTH_LONG).show();
                    return;
                }
                result.moveToFirst();
                StringBuffer buffer=new StringBuffer();

                do{
                    buffer.append("ID : "+result.getString(0)+"\n");
                    buffer.append("First Name : "+result.getString(1)+"\n");
                    buffer.append("Last Name : "+result.getString(2)+"\n");
                    buffer.append("Email : "+result.getString(3)+"\n\n");


                }while (result.moveToNext());

                Display(buffer.toString());
            }
            public void Display(String data)
            {
                T1.setText(data);
            }
        });

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean aa = my.update(E1.getText().toString(),E2.getText().toString(),E3.getText().toString(),E4.getText().toString());
                if(aa==true)
                {
                    Toast.makeText(MainActivity.this,"Updated Successfully",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this,"NOT Updated Successfully",Toast.LENGTH_LONG).show();

            }
        });

        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=my.delete(E1.getText().toString());
                    if(a>0)
                    {
                        Toast.makeText(MainActivity.this,"DELETE SUCCESSFUL",Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(MainActivity.this,"NOT DELETE SUCCESSFUL",Toast.LENGTH_LONG).show();

            }
        });

        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                E1.setText("");
                E2.setText("");
                E3.setText("");
                E4.setText("");

            }
        });
    }
}
