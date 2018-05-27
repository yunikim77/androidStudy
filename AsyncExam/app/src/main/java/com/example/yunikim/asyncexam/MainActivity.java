package com.example.yunikim.asyncexam;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button mButton_Start;
    Button mButton_Init;
    TextView mTextView;
    CAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton_Start = findViewById(R.id.btStart);
        mButton_Init = findViewById(R.id.btInit);
        mTextView = findViewById(R.id.tvCount);

        mButton_Init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTask.cancel(true);
                mTextView.setText("10");
            }
        });

        mButton_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTask = new CAsyncTask();
                asyncTask.execute();
            }
        });
    }

    class CAsyncTask extends AsyncTask<Void, Integer, Void> {
        private int mCount;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mCount = 10;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while(mCount>0) {
                try {
                    Thread.sleep(1000);
                    mCount--;
                    publishProgress(mCount);

                    if(isCancelled()) {
                        break;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            mTextView.setText(values[0].toString());
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
