package com.example.aspect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    test(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @AvoidFastClick(
            fastClickInterval = 2000L,
            fastClickTip = "快速点击"
    )
    private void test(View view) throws IOException, NumberFormatException {
        Log.d("adai", "test");
    }
}
