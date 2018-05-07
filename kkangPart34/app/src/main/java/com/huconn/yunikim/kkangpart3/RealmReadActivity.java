package com.huconn.yunikim.kkangpart3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmReadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_read);

        TextView titleView = findViewById(R.id.read_title);
        TextView contentView = findViewById(R.id.read_content);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<MemoVO> vo = mRealm.where(MemoVO.class).equalTo("title", title).findAll();
        vo.sort("content", Sort.DESCENDING);
        titleView.setText(vo.first().title);
        contentView.setText(vo.first().content);
    }
}
