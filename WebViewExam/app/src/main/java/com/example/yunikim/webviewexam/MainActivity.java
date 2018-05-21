package com.example.yunikim.webviewexam;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mAddressEdit;
    private WebView mWebView;
    private Button mMoveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.web_view);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mAddressEdit = findViewById(R.id.address_edit);
        mMoveButton = findViewById(R.id.move_button);

        mAddressEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mMoveButton.callOnClick();

                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }

    public void kukudas(View view) {
        String address = mAddressEdit.getText().toString();
        if(address.startsWith("http://") == false)
            address = "http://" + address;
        mWebView.loadUrl(address);
    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack())
            mWebView.goBack();
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_back:
                if(mWebView.canGoBack())
                    mWebView.goBack();
                break;
            case R.id.action_forward:
                if(mWebView.canGoForward())
                    mWebView.goForward();
                break;
            case R.id.action_refresh:
                mWebView.reload();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
