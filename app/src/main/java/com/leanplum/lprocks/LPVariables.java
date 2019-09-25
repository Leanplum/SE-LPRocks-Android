package com.leanplum.lprocks;

import com.leanplum.annotations.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LPvariables {

    @Variable
    public static int onboardingOption = 1;

    @Variable
    public static String welcomeMessage = "Welcome to Leanplum!";

    @Variable
    public static String callToActionText = "Call to Action";

    @Variable
    public static String mainButtonText = "Next";

    @Variable
    public static boolean newFeatureOn = false;

    @Variable
    public static String backGroundImage = "n4.jpg";

    @Variable
    public static boolean newOnBoardFlow = false;

    @Variable
    public static List<String> exampleArray = new ArrayList<String>(Arrays.asList("1","2","three","four"));

    @Variable
    public static Map<String, Object> exampleDictionary = new HashMap<String, Object>(){
        {
            put("name", "Turbo Boost");
            put("price", 150);
            put("speedMultiplier", 1.5);
            put("timeout", 15);
        }
    };

    @Variable
    public static String audioFile = "sample.m4a";

}
