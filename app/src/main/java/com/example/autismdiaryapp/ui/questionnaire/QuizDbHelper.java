package com.example.autismdiaryapp.ui.questionnaire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.example.autismdiaryapp.ui.Diary.DiaryContract;
import com.example.autismdiaryapp.ui.Games.Game4Scenarios.Response;
import com.example.autismdiaryapp.ui.Games.Game4Scenarios.Scenario;
import com.example.autismdiaryapp.ui.Games.Game4Scenarios.ScenarioContract;


public class QuizDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MainDatabase.db";
    public static final int DATABASE_VERSION = 9;


    private SQLiteDatabase db;


    public QuizDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + "(" +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT" +
                ")";


        final String SQL_CREATE_ANSWERS_TABLE = "CREATE TABLE "  +
                QuizContract.AnswersTable.TABLE_NAME  + "(" +
                QuizContract.AnswersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.AnswersTable.COLUMN_ANSWER + " TEXT" +
                ")";

        final String SQL_CREATE_SCENARIOS_TABLE = "CREATE TABLE " +
                ScenarioContract.ScenarioTable.TABLE_NAME + "(" +
                ScenarioContract.ScenarioTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ScenarioContract.ScenarioTable.COLUMN_SCENARIO + " TEXT, " +
                ScenarioContract.ScenarioTable.COLUMN_RESPONSE1 + " TEXT, " +
                ScenarioContract.ScenarioTable.COLUMN_RESPONSE2 + " TEXT, " +
                ScenarioContract.ScenarioTable.COLUMN_RESPONSE3 + " TEXT, " +
                ScenarioContract.ScenarioTable.COLUMN_RESPONSE4 + " TEXT, " +
                ScenarioContract.ScenarioTable.COLUMN_BEST_RESPONSE + " TEXT, " +
                ScenarioContract.ScenarioTable.COLUMN_WORST_RESPONSE + " TEXT" +
                ")";

        final String SQL_CREATE_RESPONSES_TABLE = "CREATE TABLE "  +
                ScenarioContract.ResponsesChosenTable.TABLE_NAME  + "(" +
                ScenarioContract.ResponsesChosenTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ScenarioContract.ResponsesChosenTable.COLUMN_FIRST_RESPONSE + " TEXT, " +
                ScenarioContract.ResponsesChosenTable.COLUMN_SECOND_RESPONSE + " TEXT, " +
                ScenarioContract.ResponsesChosenTable.COLUMN_THIRD_RESPONSE + " TEXT, " +
                ScenarioContract.ResponsesChosenTable.COLUMN_FOURTH_RESPONSE + " TEXT" +
                ")";

        final String SQL_CREATE_DIARY_TABLE = "CREATE TABLE " +
                DiaryContract.DiaryTable.TABLE_NAME + "(" +
                DiaryContract.DiaryTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DiaryContract.DiaryTable.COLUMN_DATE + " TEXT, " +
                DiaryContract.DiaryTable.COLUMN_INPUT + " TEXT, " +
                DiaryContract.DiaryTable.COLUMN_GOAL1 + " BOOLEAN, " +
                DiaryContract.DiaryTable.COLUMN_GOAL2 + " BOOLEAN, " +
                DiaryContract.DiaryTable.COLUMN_GOAL3 + " BOOLEAN, " +
                DiaryContract.DiaryTable.COLUMN_ACTIVITY + " INTEGER, " +
                DiaryContract.DiaryTable.COLUMN_SOCIAL + " INTEGER, " +
                DiaryContract.DiaryTable.COLUMN_SLEEP + " INTEGER" +
                ")" ;


                db.execSQL(SQL_CREATE_ANSWERS_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        db.execSQL(SQL_CREATE_RESPONSES_TABLE);
        db.execSQL(SQL_CREATE_SCENARIOS_TABLE);

        db.execSQL(SQL_CREATE_DIARY_TABLE);

       

        fillScenarioTable();

        fillQuestionsTable();


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.AnswersTable.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + ScenarioContract.ScenarioTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ScenarioContract.ResponsesChosenTable.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + DiaryContract.DiaryTable.TABLE_NAME);

        onCreate(db);
    }



    private void fillQuestionsTable(){
        Question q1 = new Question("What is your gender?", "Male","Female","Other");
        addQuestion(q1);
        Question q2 = new Question("Do you find that you over sensitive or under sensitive to sound/light?", "Over sensitive","Under sensitive","Neither");
        addQuestion(q2);
        Question q3 = new Question("Do you find meeting someone new stressful?", "Not at all","Somewhat","Yes");
        addQuestion(q3);
        Question q4 = new Question("How many hours do you spend each week doing exercise on average?", "4+","2-3","0-1");
        addQuestion(q4);
        Question q5 = new Question("How social do you consider yourself in a group setting?", "Very","Average","Little");
        addQuestion(q5);
        Question q6 = new Question("Do you struggle keeping up with a conversation that is not interesting?", "Not at all","Somewhat","Yes");
        addQuestion(q6);
        Question q7 = new Question("Do you feel stressed or overwhelmed often?", "Not at all","Somewhat","Yes");
        addQuestion(q7);
        Question q8 = new Question("Do you react quickly to situations?", "Not at all","Somewhat","Yes");
        addQuestion(q8);
        Question q9 = new Question("Would you consider mental mathematics your strong suit?", "Not at all","Somewhat","Yes");
        addQuestion(q9);
        Question q10 = new Question("Is it sometimes difficult to read someones emotion if they don't explicitly say it?", "Not at all","Somewhat","Yes");
        addQuestion(q10);
    }
    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);

    }

    public void addAnswer(Answer answer){
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.AnswersTable.COLUMN_ANSWER, answer.getAnswer());
        db.insert(QuizContract.AnswersTable.TABLE_NAME,null,cv);
    }



    public List<Question> getAllQuestions(){
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                questionList.add(question);

            } while(c.moveToNext());
        }
        c.close();
        return questionList;
    }

    private void fillScenarioTable(){
        Scenario s1 = new Scenario("An acquaintance greets you Good morning, what do you do? ", "Say good morning back, and make your way.","Say good morning and get into conversation.","Ignore them","Nod at them.","Say good morning back, and make your way.","Ignore them");
        addScenario(s1);
        Scenario s2 = new Scenario("You are talking in a circle of friends when one person interrupts you, what do you do?", "Bring it up politely.","Rudely remark about it.","Ignore it.","Talk over them back.","Ignore it.","Rudely remark about it.");
        addScenario(s2);

    }
    private void addScenario(Scenario scenario){
        ContentValues cv = new ContentValues();
        cv.put(ScenarioContract.ScenarioTable.COLUMN_SCENARIO, scenario.getScenario());
        cv.put(ScenarioContract.ScenarioTable.COLUMN_RESPONSE1, scenario.getResponse1());
        cv.put(ScenarioContract.ScenarioTable.COLUMN_RESPONSE2, scenario.getResponse2());
        cv.put(ScenarioContract.ScenarioTable.COLUMN_RESPONSE3, scenario.getResponse3());
        cv.put(ScenarioContract.ScenarioTable.COLUMN_RESPONSE4, scenario.getResponse4());
        cv.put(ScenarioContract.ScenarioTable.COLUMN_BEST_RESPONSE, scenario.getBestResponse());
        cv.put(ScenarioContract.ScenarioTable.COLUMN_WORST_RESPONSE, scenario.getWorstReponse());
        db.insert(ScenarioContract.ScenarioTable.TABLE_NAME, null, cv);

    }

    public void addSelectedResponses(Response response){
        ContentValues cv = new ContentValues();
        cv.put(ScenarioContract.ResponsesChosenTable.COLUMN_FIRST_RESPONSE, response.getFirstResponse());
        cv.put(ScenarioContract.ResponsesChosenTable.COLUMN_SECOND_RESPONSE, response.getSecondResponse());
        cv.put(ScenarioContract.ResponsesChosenTable.COLUMN_THIRD_RESPONSE, response.getThirdResponse());
        cv.put(ScenarioContract.ResponsesChosenTable.COLUMN_FOURTH_RESPONSE, response.getFourthResponse());
        db.insert(ScenarioContract.ResponsesChosenTable.TABLE_NAME,null,cv);
    }



    public List<Scenario> getAllScenarios(){
        List<Scenario> scenarioList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + ScenarioContract.ScenarioTable.TABLE_NAME, null);

        if(c.moveToFirst()){
            do{
                Scenario scenario = new Scenario();
                scenario.setScenario(c.getString(c.getColumnIndex(ScenarioContract.ScenarioTable.COLUMN_SCENARIO)));
                scenario.setResponse1(c.getString(c.getColumnIndex(ScenarioContract.ScenarioTable.COLUMN_RESPONSE1)));
                scenario.setResponse2(c.getString(c.getColumnIndex(ScenarioContract.ScenarioTable.COLUMN_RESPONSE2)));
                scenario.setResponse3(c.getString(c.getColumnIndex(ScenarioContract.ScenarioTable.COLUMN_RESPONSE3)));
                scenario.setResponse4(c.getString(c.getColumnIndex(ScenarioContract.ScenarioTable.COLUMN_RESPONSE4)));
                scenarioList.add(scenario);

            } while(c.moveToNext());
        }
        c.close();
        return scenarioList;
    }


    public boolean addData( String date, String input, Boolean goal1, Boolean goal2, Boolean goal3,Integer activity,Integer social,Integer sleep)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DiaryContract.DiaryTable.COLUMN_DATE,date);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_INPUT,input);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_GOAL1,goal1);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_GOAL2,goal2);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_GOAL3,goal3);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_ACTIVITY,activity);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_SOCIAL,social);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_SLEEP,sleep);
        long result = db.insert(DiaryContract.DiaryTable.TABLE_NAME, null, contentValues);


        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }


    //diary
    public boolean updateData(String date,String input,Boolean goal1,Boolean goal2,Boolean goal3,Integer activity,Integer social,Integer sleep)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DiaryContract.DiaryTable.COLUMN_DATE,date);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_INPUT,input);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_GOAL1,goal1);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_GOAL2,goal2);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_GOAL3,goal3);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_ACTIVITY,activity);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_SOCIAL,social);
        contentValues.put(DiaryContract.DiaryTable.COLUMN_SLEEP,sleep);
        db.update(DiaryContract.DiaryTable.TABLE_NAME, contentValues, "Date = ?",new String[] { date });
        return true;
    }

    public Cursor getData(String TableName, String dbfield, String date) {
        db = getReadableDatabase();
        String Query = "SELECT * FROM " + TableName + " WHERE " + dbfield + " = '"+ date+"'";
        Cursor res = db.rawQuery(Query, null);
        return res;
    }

}
