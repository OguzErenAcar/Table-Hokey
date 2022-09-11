package com.example.a1hokey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    boolean b1;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Basla=(Button)findViewById(R.id.button);
        Button kisi2=(Button)findViewById(R.id.player2);
        Button Cık=(Button)findViewById(R.id.cikis);

        intent=new Intent(MainActivity.this,oyun.class);





        Basla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             intent.putExtra("b1",false);
             startActivity(intent);
            }
        });

        kisi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("b1",true);
                startActivity(intent);

            }
        });

        Cık.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });





    }
}
