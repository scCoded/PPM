package com.example.autismdiaryapp.ui.questionnaire;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract (){}

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "questions";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";

    }

    public static class UsersTable implements BaseColumns{
        public static final String TABLE_NAME = "quiz_users";
        public static final String COLUMN_NAME = "name";
    }

    public static class AnswersTable implements BaseColumns{
        public static final String TABLE_NAME = "quiz_answers";
        public static final String COLUMN_USER_ID = "userID";
        public static final String COLUMN_QUESTION_ID = "questionID";
        public static final String COLUMN_ANSWER = "answer";
    }
}
