package com.sysoutachieve.bottombartest;

import android.support.annotation.IdRes;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    private CallLogFragment callLogFragment;
    private ContactsFragment contactsFragment;
    private SetMacroFragment setMacroFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        callLogFragment = new CallLogFragment();
        contactsFragment = new ContactsFragment();
        setMacroFragment = new SetMacroFragment();

        initFragment();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                if (tabId == R.id.tab_call_log) {
                    transaction.replace(R.id.contentContainer, callLogFragment).commit();
                } else if (tabId == R.id.tab_contacts) {
                    transaction.replace(R.id.contentContainer, contactsFragment).commit();
                } else if (tabId == R.id.tab_macro_setting) {
                    transaction.replace(R.id.contentContainer, setMacroFragment).commit();
                }
            }
        });

    }

    public void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentContainer, callLogFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
