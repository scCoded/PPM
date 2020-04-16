package com.example.autismdiaryapp.ui.Games.Game1Reaction;

import android.provider.BaseColumns;

public final class Game1Contract {

    private Game1Contract(){}

    public static class Game1Table implements BaseColumns {
        public static final String TABLE_NAME = "Game1_table";
        public static final String COLUMN_SCORE1 = "Score";

    }
}
