package com.naveen.photosviewerassignment.photocollection;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import com.naveen.photosviewerassignment.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver MyReceiver = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        MyReceiver = new MyReceiver();
        registerBroadcastIntent();

        gotoPictureCollectionFragement(savedInstanceState);
    }

    private void  registerBroadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }

    private void gotoPictureCollectionFragement(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.mainActivity, PictureCollectionFragment.class, null)
                    .commit();
        }
    }
}