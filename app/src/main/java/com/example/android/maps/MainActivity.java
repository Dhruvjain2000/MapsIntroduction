package com.example.android.maps;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Maps";
    public static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isServivesOK()){
            init();
        }
    }

    private void init() {
        Button btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServivesOK() {
        Log.e(TAG, "Checking google services version");
        int availabe = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (availabe == ConnectionResult.SUCCESS) {
            Log.e(TAG, "isServivesOK: Google play services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(availabe)) {
            Log.e(TAG, "isServivesOK: an error occured but can be resolved");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, availabe, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else
            Toast.makeText(MainActivity.this, "We can't make map!", Toast.LENGTH_SHORT).show();

        return false;
    }

}
