package com.example.a77_da_000.brick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ControlActivity extends AppCompatActivity {
    boolean l1,l2,l3,m1,m2,m3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        final ImageButton led1btn = (ImageButton) findViewById(R.id.led1btn);
        final ImageButton led2btn = (ImageButton) findViewById(R.id.led2btn);
        final ImageButton led3btn = (ImageButton) findViewById(R.id.led3btn);
        final ImageButton mot1btn = (ImageButton) findViewById(R.id.mot1btn);
        final ImageButton mot2btn = (ImageButton) findViewById(R.id.mot2btn);
        final ImageButton mot3btn = (ImageButton) findViewById(R.id.mot3btn);

        led1btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                l1 = !l1;
                if(l1){
                    led1btn.setBackgroundResource(R.drawable.oval_on);
                    led1btn.setImageResource(R.drawable.led_on);
                }
                else{
                    led1btn.setBackgroundResource(R.drawable.oval);
                    led1btn.setImageResource(R.drawable.led);
                }
            }
        });
        led2btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                l2 = !l2;
                if(l2){
                    led2btn.setBackgroundResource(R.drawable.oval_on);
                    led2btn.setImageResource(R.drawable.led_on);
                }
                else{
                    led2btn.setBackgroundResource(R.drawable.oval);
                    led2btn.setImageResource(R.drawable.led);
                }
            }
        });
        led3btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                l3 = !l3;
                if(l3){
                    led3btn.setBackgroundResource(R.drawable.oval_on);
                    led3btn.setImageResource(R.drawable.led_on);
                }
                else{
                    led3btn.setBackgroundResource(R.drawable.oval);
                    led3btn.setImageResource(R.drawable.led);
                }
            }
        });
        mot1btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                m1 = !m1;
                if(m1){
                    mot1btn.setBackgroundResource(R.drawable.oval_on);
                    mot1btn.setImageResource(R.drawable.motor_on);
                }
                else{
                    mot1btn.setBackgroundResource(R.drawable.oval);
                    mot1btn.setImageResource(R.drawable.motor);
                }
            }
        });
        mot2btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                m2 = !m2;
                if(m2){
                    mot2btn.setBackgroundResource(R.drawable.oval_on);
                    mot2btn.setImageResource(R.drawable.motor_on);
                }
                else{
                    mot2btn.setBackgroundResource(R.drawable.oval);
                    mot2btn.setImageResource(R.drawable.motor);
                }
            }
        });
        mot3btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                m3 = !m3;
                if(m3){
                    mot3btn.setBackgroundResource(R.drawable.oval_on);
                    mot3btn.setImageResource(R.drawable.motor_on);
                }
                else{
                    mot3btn.setBackgroundResource(R.drawable.oval);
                    mot3btn.setImageResource(R.drawable.motor);
                }
            }
        });
    }
}
