package com.goodlogic.a19_recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){

        final MyDialog dialog = new MyDialog(this);

        dialog.setCancelable(false);
        dialog.setOwnerActivity(this);

        View.OnClickListener okl = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               dialog.dismiss();
            }
        };

        dialog.setClickListener(okl);
    }
}
