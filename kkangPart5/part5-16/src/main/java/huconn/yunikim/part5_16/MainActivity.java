package huconn.yunikim.part5_16;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    MyThread thread;
    ImageButton btStart;
    ImageButton btStop;
    private boolean isStop = false;
    private boolean isProcessing = false;

    static class MyHandler extends Handler {
        private final WeakReference<MainActivity> weakReference;
        MainActivity act;

        public MyHandler(MainActivity activity) {
            this.weakReference = new WeakReference<>(activity);
            act = activity;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1) {
                act.textView.setText(String.valueOf(msg.arg1));
                Log.d("핸들러", String.valueOf(msg.arg1) + "번째 카운트 설정");
            }
            else if(msg.what == 2) {
                act.textView.setText((String)msg.obj);
                Log.d("핸들러", "LAST Handling");
            }
        }
    }

    MyHandler handler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btStart = findViewById(R.id.btStart);
        btStop = findViewById(R.id.btStop);

        btStart.setOnClickListener(this);
        btStop.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v == btStart) {
            isStop = false;
            if(!isProcessing) {
                isProcessing = true;
                thread = new MyThread();
                thread.start();
            }
        }
        if(v == btStop) {
            isStop = true;
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();

            int cnt=10;

            while(isProcessing)
            {
                if(!isStop) {
                    cnt--;
                    Message msg = new Message();
                    msg.what = 1;
                    msg.arg1 = cnt;
                    handler.sendMessage(msg);
                    Log.d("쓰레드", String.valueOf(cnt)+"번째 카운트 메시지 전달");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        isProcessing = false;
                    }

                    if(cnt <= 0) {
                        msg = new Message();
                        msg.what = 2;
                        msg.obj = "The END";
                        handler.sendMessage(msg);
                        Log.d("쓰레드", "The END");
                        isProcessing = false;
                    }
                }
            }
        }
    }
}
