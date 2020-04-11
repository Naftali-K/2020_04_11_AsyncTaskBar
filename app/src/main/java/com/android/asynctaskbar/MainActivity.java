package com.android.asynctaskbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar bar, bar2;
    TextView percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = findViewById(R.id.progressBar);
        bar2 = findViewById(R.id.progressBar2);
        percent = findViewById(R.id.text_percent);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyProgressBarAsyncTask().execute();
            }
        });
    }


    class MyProgressBarAsyncTask extends AsyncTask<Void, Integer, Void>{

        private int barStatus = 0;

        //Method -  what happened when start work ProgressBar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getBaseContext(), "Start Bar run", Toast.LENGTH_LONG).show();
        }

        //Method -  what happened when finish work ProgressBar
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getBaseContext(), "Bar Finished", Toast.LENGTH_LONG).show();
        }

        //Method - display update of ProgressBar
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            //change color of bar
            if(values[0] >= 80){
                bar2.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                //bar2.setProgressTintList(ColorStateList.valueOf(Color.RED));
                Toast.makeText(getBaseContext(), "Change Color", Toast.LENGTH_LONG).show();
            }

            bar.setProgress(values[0]);
            bar2.setProgress(values[0]);
            percent.setText(values[0] + "%");
        }

        //Method -  what happened in background, when going update of ProgressBar
        @Override
        protected Void doInBackground(Void... voids) {

            while (barStatus < 100){
                barStatus++;
                publishProgress(barStatus);
                SystemClock.sleep(100);
            }

            return null;
        }
    }
}
