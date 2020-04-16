package com.example.autismdiaryapp.ui.Games.Game4Scenarios;

import android.provider.BaseColumns;

public final class ScenarioContract {

    private ScenarioContract(){}

    public static class ScenarioTable implements BaseColumns {
        public static final String TABLE_NAME = "scenario_table";
        public static final String COLUMN_SCENARIO = "scenarios";
        public static final String COLUMN_RESPONSE1 = "response1";
        public static final String COLUMN_RESPONSE2 = "response2";
        public static final String COLUMN_RESPONSE3 = "response3";
        public static final String COLUMN_RESPONSE4 = "response4";
        public static final String COLUMN_BEST_RESPONSE = "best_response";
        public static final String COLUMN_WORST_RESPONSE = "worst_response";

    }


    public static class ResponsesChosenTable implements BaseColumns{
        public static final String TABLE_NAME = "responses_chosen";
        public static final String COLUMN_FIRST_RESPONSE= "first_response";
        public static final String COLUMN_SECOND_RESPONSE= "second_response";
        public static final String COLUMN_THIRD_RESPONSE= "third_response";
        public static final String COLUMN_FOURTH_RESPONSE= "fourth_response";

    }
}

