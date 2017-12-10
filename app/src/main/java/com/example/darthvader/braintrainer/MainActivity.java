package com.example.darthvader.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btngo,b1,b2,b3,b4,btnplayagain;
    TextView tvTime,tvDisplay,tvCorrect,tvPoints;
    int locationofcorrectans;
    int questions,scores;
    RelativeLayout layout;
    CountDownTimer countDownTimer;
    public void playagain(View view)
    {
        questions=0;
        scores=0;
        //countDownTimer=null;
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        btngo.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.VISIBLE);
        start();
        generate();
        tvCorrect.setText(null);

    }
    public void start()
    {
        countDownTimer=new CountDownTimer(30200,1000)
        {
            @Override
            public void onTick(long l) {
                tvTime.setText(Integer.toString((int)l/1000)+"s");
            }

            @Override
            public void onFinish() {
                       btnplayagain.setVisibility(View.VISIBLE);
                       b1.setEnabled(false);
                       b2.setEnabled(false);
                       b3.setEnabled(false);
                       b4.setEnabled(false);
                       tvPoints.setText("0/0");
                       tvCorrect.setText("Points won is "+Integer.toString(scores)+"/"+Integer.toString(questions));
            }
        }.start();

    }
    ArrayList<Integer> list=new ArrayList<Integer>();
    public void correctAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationofcorrectans)))
        {
            tvCorrect.setText("Correct!");
            scores++;
            questions++;
            tvPoints.setText(Integer.toString(scores)+"/"+Integer.toString(questions));
            generate();

        }
        else
        {
            tvCorrect.setText("Wrong!");
            questions++;
            tvPoints.setText(Integer.toString(scores)+"/"+Integer.toString(questions));
            generate();

        }

    }

    public void generate() {
        btnplayagain.setVisibility(View.INVISIBLE);
        //layout.setEnabled(true);
        Random rand = new Random();
        int a, b;
        int incorrect;
        a = rand.nextInt(21);
        b = rand.nextInt(21);
        list.clear();
        tvDisplay.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationofcorrectans = rand.nextInt(4);
        for (int i=0;i<4;i++)
        {
            if(i==locationofcorrectans)
            {
                list.add(a+b);
            }
            else
            {
                incorrect=rand.nextInt(41);
                while (incorrect==a+b)
                    incorrect=rand.nextInt(41);
                list.add(incorrect);

            }
        }
        b1.setText(Integer.toString(list.get(0)));
        b2.setText(Integer.toString(list.get(1)));
        b3.setText(Integer.toString(list.get(2)));
        b4.setText(Integer.toString(list.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scores=0;
        questions=0;
        tvDisplay=(TextView)findViewById(R.id.tv_display);
        btngo=(Button)findViewById(R.id.btn_go);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button1);
        b3=(Button)findViewById(R.id.button2);
        b4=(Button)findViewById(R.id.button3);
        btnplayagain=(Button)findViewById(R.id.btn_playagain);
        tvTime=(TextView)findViewById(R.id.tv_time);
        tvCorrect=(TextView)findViewById(R.id.tv_correct);
        tvPoints=(TextView)findViewById(R.id.tv_points);
        layout=(RelativeLayout)findViewById(R.id.rl_inter);
        layout.setVisibility(View.INVISIBLE);
        btngo.setVisibility(View.VISIBLE);

    }
}
