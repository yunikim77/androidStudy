package com.huconn.yunikim.part3_9;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.BaseAdapter;

public class SettingPreferenceFragment extends PreferenceFragment {
    SharedPreferences prefs;
    ListPreference soundPreference;
    ListPreference keywordSoundPreference;
    PreferenceScreen keywordScreen;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preference);

        soundPreference = (ListPreference)findPreference("sound_list");
        keywordSoundPreference =(ListPreference)findPreference("keyword_sound_list");
        keywordScreen = (PreferenceScreen)findPreference("keyword_screen");
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!prefs.getString("sound_list", "").equals("")) {
            soundPreference.setSummary(prefs.getString("sound_list", "강대근 목소리"));
            Log.d("로그","여기 들어왔다");
        }
        else {
            Log.d("로그", "메롱메롱!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

        if(!prefs.getString("keyword_sound_list", "").equals("")) {
            keywordSoundPreference.setSummary(prefs.getString("keyword_sound_list", "강대근 목소리"));
        }

        if(prefs.getBoolean("keyword", false)) {
            keywordScreen.setSummary("사용");
        }
        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Log.d("로그", "실행실행실행 -----------------------");
            if (key.equals("sound_list")) {
                soundPreference.setSummary(prefs.getString("sound_list", ""));
            }
            if (key.equals("keyword_sound_list")) {
                keywordSoundPreference.setSummary(prefs.getString("keyword_sound_list", ""));
            }
            if (key.equals("keyword")) {
                Log.d("로그", "사용용용용용 +++++++++++++@@@@@@@@@");
                if (prefs.getBoolean("keyword", false)) {
                    Log.d("로그", "444444444444444444444444444");
                    keywordScreen.setSummary("사용");
                } else {
                    Log.d("로그", "9999999999999999999999777777777777");
                    keywordScreen.setSummary("사용안함");
                }
                ((BaseAdapter)getPreferenceScreen().getRootAdapter()).notifyDataSetChanged();
            }
        }
    };
}
