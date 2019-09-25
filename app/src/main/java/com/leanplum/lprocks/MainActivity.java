package com.leanplum.lprocks;

import com.leanplum.Leanplum;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.leanplum.callbacks.VariablesChangedCallback;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int screenNumber = 0;

    HashMap<Integer, List<Integer>> onboardingScreensMap = new HashMap<Integer, List<Integer>>(){{
        this.put(1, new ArrayList<Integer>(Arrays.asList(R.drawable.night_sky, R.drawable.snowy_mountain, R.drawable.eiffel_tower)));
        this.put(2, new ArrayList<Integer>(Arrays.asList(R.drawable.lion, R.drawable.northern_lights, R.drawable.old_street)));
        this.put(3, new ArrayList<Integer>(Arrays.asList(R.drawable.pink_leaves, R.drawable.red_night_sky, R.drawable.sunset)));
    }};

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get current screen number
        Bundle b = getIntent().getExtras();
        if (b != null){
            screenNumber = b.getInt("screenNumber", 0);
        }

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.background);
        final Button mainNextButton = (Button) findViewById(R.id.mainNextButton);

        List<Integer> screens =  onboardingScreensMap.get(LPvariables.onboardingOption);

        final boolean isFinalScreen = (screenNumber == screens.size()-1);

        if (isFinalScreen){
            mainNextButton.setText("Back");
        }


        Integer background = screens.get(screenNumber);
        layout.setBackgroundResource(background);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




        mainNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextScreenNumber;
                if (isFinalScreen){
                    nextScreenNumber = 0;
                } else{
                    nextScreenNumber = screenNumber + 1;
                }
                nextScreen(nextScreenNumber);
            }
        });

        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                System.out.println("yoyo");
                if (!isFinalScreen){
                    mainNextButton.setText(LPvariables.mainButtonText);
                }
            }
        });
    }

    public void nextScreen(int nextScreenNumber){
        Intent intent = new Intent(this, this.getClass());
        intent.putExtra("screenNumber", nextScreenNumber);
        startActivity(intent);
        finish();
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
