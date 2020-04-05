package com.example.autismdiaryapp.ui.Diary;

import android.provider.BaseColumns;

public final class DiaryContract {

    private DiaryContract (){}

    public static class DiaryTable implements BaseColumns {

        public static final String TABLE_NAME = "diary_info";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_INPUT = "input";
        public static final String COLUMN_GOAL1 = "goal1";
        public static final String COLUMN_GOAL2 = "goal2";
        public static final String COLUMN_GOAL3 = "goal3";
        public static final String COLUMN_ACTIVITY = "activity";
        public static final String COLUMN_SOCIAL = "social";
        public static final String COLUMN_SLEEP = "sleep";

    }

}
