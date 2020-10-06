package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BasicTestActivity extends AppCompatActivity {

    static int taskType;

    static public int mFinalResult;
    int flag_next, flag1, flag2, flag3, flag4, count, ans;
    int getflag1, getflag2, getflag3, getflag4, to_final;
    Button mButtonAns1, mButtonAns2, mButtonAns3, mButtonAns4, mButtonNext;
    ProgressBar progressBar;
    TextView mTaskText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_test);

        mTaskText = findViewById(R.id.task_text);
        mFinalResult = 0;

        //Set onClicks
        flag1 = 0; flag2 = 0; flag3 = 0; flag4 = 0;
        getflag1 = 0; getflag2 = 0; getflag3 = 0; getflag4 = 0;
        mButtonAns1 = findViewById(R.id.button1);
        mButtonAns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag1==0){
                    mButtonAns1.setBackgroundResource(R.drawable.button_ans_click);
                    flag1 = 1;
                } else{
                    mButtonAns1.setBackgroundResource(R.drawable.button_ans_unclick);
                    flag1 = 0;
                }
            }
        });
        mButtonAns2 = findViewById(R.id.button2);
        mButtonAns2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag2==0){
                    mButtonAns2.setBackgroundResource(R.drawable.button_ans_click);
                    flag2 = 1;
                } else{
                    mButtonAns2.setBackgroundResource(R.drawable.button_ans_unclick);
                    flag2 = 0;
                }
            }
        });
        mButtonAns3 = findViewById(R.id.button3);
        mButtonAns3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag3==0){
                    mButtonAns3.setBackgroundResource(R.drawable.button_ans_click);
                    flag3 = 1;
                } else{
                    mButtonAns3.setBackgroundResource(R.drawable.button_ans_unclick);
                    flag3 = 0;
                }
            }
        });
        mButtonAns4 = findViewById(R.id.button4);
        mButtonAns4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag4==0){
                    mButtonAns4.setBackgroundResource(R.drawable.button_ans_click);
                    flag4 = 1;
                } else{
                    mButtonAns4.setBackgroundResource(R.drawable.button_ans_unclick);
                    flag4 = 0;
                }
            }
        });

        change_task();


        //To next task
        progressBar = findViewById(R.id.progressBar);
        mButtonNext = findViewById(R.id.button_ans);
        flag_next = 0; count = 0;
        to_final = 0;
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count==10 && to_final==0){
                    to_final = 1;
                    progressBar.setProgress(10);
                    mButtonNext.setText("Завершить");
                } else if (to_final==1){
                    Intent intent = new Intent(BasicTestActivity.this, FinalTestActivity.class);
                    startActivity(intent);
                }
                    else{
                    if (flag_next==0){
                        ans = 0;
                        check_button(mButtonAns1, getflag1, flag1);
                        check_button(mButtonAns2, getflag2, flag2);
                        check_button(mButtonAns3, getflag3, flag3);
                        check_button(mButtonAns4, getflag4, flag4);

                        if (ans==0){
                            mFinalResult += 1;
                        }

                        count += 1;
                        flag_next = 1;
                        if (count==10){
                            mButtonNext.setText("Завершить");
                            to_final = 1;
                            progressBar.setProgress(10);
                        }else{
                            mButtonNext.setText("Далее");
                        }
                    }
                    else{
                        progressBar.setProgress(count);
                        change_task();
                        flag_next = 0;
                        all_buttons_to_false();
                        mButtonNext.setText("Проверить");

                    }
                }
            }
        });

    }


    public void check_button(Button button, int get_flag, int click_flag){
        if (get_flag==click_flag){
            if (click_flag==1){
                button.setBackgroundResource(R.drawable.round_button4);
            }
        } else{
            button.setBackgroundResource(R.drawable.round_button3);
            ans += 1;
        }
    }

    public void change_task(){
        String[] mAns = {"", "", "", ""};
        int[] mKeys = {0, 0, 0, 0};
        switch (taskType){
            case 1:
                mTaskText.setText(TasksRus.mType1Text);
                TasksRus.chose_vars(TasksRus.mType1Correct, TasksRus.mType1Incorrect, mAns, mKeys);
                mButtonAns1.setText(mAns[0]); mButtonAns2.setText(mAns[1]); mButtonAns3.setText(mAns[2]); mButtonAns4.setText(mAns[3]);
                getflag1 = mKeys[0]; getflag2 = mKeys[1]; getflag3 = mKeys[2]; getflag4 = mKeys[3];
                break;
        }
    }

    public void all_buttons_to_false(){
        flag1 = 0; flag2 = 0; flag3 = 0; flag4 = 0;
        mButtonAns1.setBackgroundResource(R.drawable.button_ans_unclick);
        mButtonAns2.setBackgroundResource(R.drawable.button_ans_unclick);
        mButtonAns3.setBackgroundResource(R.drawable.button_ans_unclick);
        mButtonAns4.setBackgroundResource(R.drawable.button_ans_unclick);
    }


}