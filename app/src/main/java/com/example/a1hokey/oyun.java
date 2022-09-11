package com.example.a1hokey;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.Math;


public class oyun extends AppCompatActivity {

    //object
    public ImageView player1, ball;
    public ImageView player2;

    public FrameLayout cerceve;
    private float cerceveBoy, cerceveGen;
    public boolean hareket;


    private float player1X, player1Y, player1length, player1width;
    private float player2X, player2Y, player2length, player2width;

    private float golge_p1X, golge_p1Y;
    private float golge_p2X, golge_p2Y;

    private float gecici_1X, gecici_1Y;
    private float gecici_2X, gecici_2Y;
    //timer
    private float yenix;
    private float yeniy;
    private float k, s;
    private boolean hizlanma, b1, kontrol;

    private int b2;

    //ball
    private float topMerX, topMerY, topUzun, topGen;
    private double merkezfark_p1;                                                     //top ile oyuncular arası merkezlerin uzunlukları
    private float playerMerX, playerMerY;

    private boolean duvaracarpma, kaydetme, sabit_hareket;

    int işaret1;
    int işaret2;
    int a;
    private int p1Gol;
    private int p2Gol;

    TextView Score;
    Intent intent, intent3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun);
        ball = findViewById(R.id.ball);
        player1 = (ImageView) findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        cerceve = findViewById(R.id.cerceve);
        Score = (TextView) findViewById(R.id.Score);

        intent3 = new Intent(oyun.this, SkorEkran.class);




        b1 = false;
        hareket = true;
        duvaracarpma = true;
        hizlanma = false;

        kaydetme = false;
        sabit_hareket = false;

        intent = getIntent();
        b1 = intent.getExtras().getBoolean("b1");


        p1Gol = 0;
        p2Gol = 0;

        Oyunbaşlat_skor_bitir();


        b2 = 0;
    }//Oncreate
            public void Oyunbaşlat_skor_bitir() {

                Score.setText(p2Gol + "-" + p1Gol);
                     //skorun tabloya yazılması

                if (p1Gol == 3 || p2Gol == 3) {
                    intent3.putExtra("b3", p1Gol + "-" + p2Gol);
                    startActivity(intent3);
                } else {
                    player1.animate().x(395).y(1315);
                    player2.animate().x(375).y(470);
                    ball.animate().x(420).y(920);
                    player1Oyunda();


                }
                if (b1) {  //start
                    player2.setVisibility(View.VISIBLE);
                    player2Oyunda();

                }

            }


    @SuppressLint("ClickableViewAccessibility")
    public void player1Oyunda() {

        player1.setOnTouchListener(new View.OnTouchListener() {     //<---- Haraket
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cerceveBoy = cerceve.getHeight();
                cerceveGen = cerceve.getWidth();

                player1length = player1.getHeight();
                player1width = player1.getWidth();

                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:
                        player1X = player1.getX() - event.getRawX();
                        player1Y = player1.getY() - event.getRawY();

                        gecici_1X = player1.getX();
                        gecici_1Y = player1.getY();

                        break;
                    case MotionEvent.ACTION_MOVE:

                        player1.animate().x(event.getRawX() + player1X).y(event.getRawY() + player1Y).setDuration(0).start();


                        golge_p1X = gecici_1X;
                        golge_p1Y = gecici_1Y;

                        gecici_1X = player1.getX();
                        gecici_1Y = player1.getY();

                        golge_p1X = gecici_1X - golge_p1X;
                        golge_p1Y = gecici_1Y - golge_p1Y;

                        sahaSiniri_p1();


                        break;
                    case MotionEvent.ACTION_UP:


                        break;

                    default:
                        return false;
                }

                TopOyunda(golge_p1X, golge_p1Y, player1.getX(), player1.getY());
                return true;
            }
        });
    }   //saha sınırı p1 ve top oyunda

    public void TopOyunda(float golgeX, float golgeY, float playerx, float playery) {
        a = 20;
        final Timer timer = new Timer(10000, a);

        topUzun = ball.getHeight();
        topGen = ball.getWidth();

        topMerX = (float) (ball.getWidth() / 2) + ball.getX();
        topMerY = (float) (ball.getHeight() / 2) + ball.getY();

        playerMerX = playerx + (float) (player1.getWidth()) / 2;
        playerMerY = playery + (float) (player1.getHeight()) / 2;

        merkezfark_p1 = Math.sqrt(Math.pow(topMerX - playerMerX, 2) + Math.pow(playerMerY - topMerY, 2));

        if (merkezfark_p1 < 181) {
            b2 = 0;
            ball.setX(ball.getX() + golgeX);
            ball.setY(ball.getY() + golgeY);

            yenix = golgeX;
            yeniy = golgeY;
            işaret1 = (int) (golgeX / golgeX);
            işaret2 = (int) (golgeY / golgeY);
            int bölüm = 10;
            k = yenix / bölüm;
            s = yeniy / bölüm;

            hizlanma = true;

            kontrol = true;
            timer.start();

        }

    }//timer



    @SuppressLint("ClickableViewAccessibility")
    public void player2Oyunda() {
        player2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cerceveBoy = cerceve.getHeight();
                cerceveGen = cerceve.getWidth();

                player2length = player2.getHeight();
                player2width = player2.getWidth();
                //final Timer timer2 =new Timer(1200,20);

                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:   //nesne SADECE tıklamayı kontrol ediyor
                        player2X = player2.getX() - event.getRawX();  //basılı tutulan yer ile kordinat farkları
                        //  System.out.println("BASILI"+player1.getX()+" "+player1.getY());
                        player2Y = player2.getY() - event.getRawY();

                        gecici_2X = player1.getX();
                        gecici_2Y = player1.getY();
                        // System.out.println(player1.getY()+"-"+event.getRawY());
                        break;
                    case MotionEvent.ACTION_MOVE://animate() hareketlilik özelliklerini döndürür

                        player2.animate().x(event.getRawX() + player2X).y(event.getRawY() + player2Y).setDuration(0).start();

                        //  System.out.println("x te:"+event.getRawX()+"+"+player1X+"y de"+event.getRawY()+"+"+player1Y);//o kordinatı burda ekliyoruz

                        golge_p2X = gecici_2X;
                        golge_p2Y = gecici_2Y;

                        gecici_2X = player2.getX();
                        gecici_2Y = player2.getY();

                        golge_p2X = gecici_2X - golge_p2X;
                        golge_p2Y = gecici_2Y - golge_p2Y;


                        sahaSiniri_p2();
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("action up ");


                        break;

                    default:
                        return false;
                }
                TopOyunda(golge_p2X, golge_p2Y, player2.getX(), player2.getY());

                return true;
            }
        });


    }//saha sınırı p2 ve top oyunda

    public void sahaSiniri_p1() {


        //  System.out.println(player1Boy);
        if (player1.getY() + player1length >= cerceveBoy) {
            player1.animate().y(cerceveBoy - player1length);
            player1.setY(cerceveBoy - player1length);

        }
        if (player1.getY() <= 80 + (cerceveBoy / 2)) {
            player1.animate().y(80 + (cerceveBoy / 2));
            player1.setY(80 + (cerceveBoy / 2));
        }
        if (player1.getX() + player1width >= cerceveGen) {
            player1.animate().x(cerceveGen - player1width);
            player1.setX(cerceveGen - player1width);
        }
        if (player1.getX() <= 10) {
            player1.animate().x(10);
            player1.setX(10);

        }

    }

    public void sahaSiniri_p2() {

        if (player2.getY() + player2length >= 80 + (cerceveBoy / 2)) {
            player2.animate().y(80 + (cerceveBoy / 2) - player2length);

        }
        if (player2.getY() <= 160) {
            player2.animate().y(160);
        }
        if (player2.getX() + player2width >= cerceveGen) {
            player2.animate().x(cerceveGen - player2width);
        }
        if (player2.getX() <= 10) {
            player2.animate().x(10);

        }

    }




    class Timer extends CountDownTimer {

        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }


        @Override
        public void onTick(long millisUntilFinished) {

            if (kontrol) {
                if (hizlanma) {
                    ball.setX(ball.getX() + yenix);
                    ball.setY(ball.getY() + yeniy);
                    yenix -= k;
                    yeniy -= s;
                    b2++;
                    if (b2 == 9) {
                        hizlanma = false;
                        sabit_hareket = true;
                    }
                } else if (sabit_hareket) {

                    b2 = 0;
                    ball.setX(ball.getX() + k);//????
                    ball.setY(ball.getY() + s);
                }
                if (ball.getX() <= 10 || ball.getWidth() + ball.getX() >= cerceveGen) {
                    yenix = yenix * (-1);
                    k = -k;
                }
                if (ball.getY() <= 160) {
                    if (300 < ball.getX() && ball.getX() < 520) {
                        p1Gol += 1;
                        ball.animate().y(ball.getY() - 40);
                        kontrol = false;
                        Oyunbaşlat_skor_bitir();
                    }
                    yeniy = yeniy * (-1);
                    s = -s;

                }if (ball.getY() + ball.getHeight() >= cerceveBoy) {

                    if (300 < ball.getX() && ball.getX() < 520) {

                        p2Gol += 1;
                        ball.animate().y(ball.getY() + 40);
                        kontrol = false;
                        Oyunbaşlat_skor_bitir();

                    }
                    yeniy = yeniy * (-1);
                    s = -s;
                }
            } }@Override
            public void onFinish() {
    }
    }
}











