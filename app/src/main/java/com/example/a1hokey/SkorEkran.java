package com.example.a1hokey;



import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class SkorEkran extends AppCompatActivity {

    private TextView Kazanan,SonScore;
    private Intent intent,intent2;
    private String b3;
    private char b2;
    private int D;
    private Button back,quit;
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor_ekran);
        Kazanan=(TextView)findViewById(R.id.textView4);
        SonScore=(TextView)findViewById(R.id.skor);
        back=findViewById(R.id.back);
      //  quit=findViewById(R.id.button3);
        intent2=new Intent(SkorEkran.this,MainActivity.class);

         intent = getIntent();
         b3=intent.getExtras().getString("b3");
        SonScore.setText(b3);

        b2=b3.charAt(2);
        D=Integer.valueOf(b2);

        System.out.println(D);

       if(D==51)//ASCII KARŞILIĞINDA 3
            Kazanan.setText(" !Player2 Kazandı.!");



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);

            }
        });


    }




}
