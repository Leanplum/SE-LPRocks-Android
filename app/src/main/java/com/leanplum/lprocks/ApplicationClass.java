package com.leanplum.lprocks;

import android.app.Application;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.annotations.Parser;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;

public class ApplicationClass extends Application {


    @Variable
    public static int onboardingOptionAndroid = 1;

    @Override
    public void onCreate() {
        super.onCreate();

        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);

        Parser.parseVariablesForClasses(LPvariables.class);
        //  For session lifecyle tracking.
        LeanplumActivityHelper.enableLifecycleCallbacks(this);

        // Insert your API keys here.
        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("app_rKwNADoDN5YDPalKRjcaIbLv22iD2DKLG5fXgtab7fc", "dev_TgboGJjukA3QBy7uIXgwDZTD6Q99MRQYzyK6gR7v9vA");
        } else {
            Leanplum.setAppIdForProductionMode("app_rKwNADoDN5YDPalKRjcaIbLv22iD2DKLG5fXgtab7fc", "prod_o10RUtXEZ8fvaTNldqDQQrzJpmxgO2YMXBvAgoVF10U");
        }

        // Optional: Tracks all screens in your app as states in Leanplum.
        // Leanplum.trackAllAppScreens();

        // This will only run once per session, even if the activity is restarted.
        Leanplum.start(this, "david.chock@leanplum.com");


        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                System.out.println("variable changed!");
            }
        });

    }
}
