package huconn.yunikim.part5_16;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    MyThread thread;
    ImageButton btStart;
    ImageButton btStop;
    private boolean isStop = false;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btStart = findViewById(R.id.btStart);
        btStop = findViewById(R.id.btStop);

        btStart.setOnClickListener(this);

        thread = new MyThread();

    }

    @Override
    public void onClick(View v) {
        if(v == btStart) {
            if(isFirst) {
                isFirst = false;
                isStop = false;
                thread.run();
            }
        }
        if(v == btStop) {
            isStop = true;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            if(msg.what == 1) {
                textView.setText(String.valueOf(msg.arg1));
                Log.d("핸들러", String.valueOf(msg.arg1)+"번째 카운트 설정");
            }
        }
    };

    class MyThread extends Thread {
        @Override
        public void run() {
            //super.run();

            for(int cnt=10;cnt>=0;cnt--) {
                if(!isStop) {
                    try {
                        Thread.sleep(1000);
                        Message msg = new Message();
                        msg.what = 1;
                        msg.arg1 = cnt;
                        handler.sendMessage(msg);
                        Log.d("쓰레드", String.valueOf(cnt)+"번째 카운트 메시지 전달");
                    } catch (Exception e) {
                        finish();
                    }
                }
            }
        }
    }
}
