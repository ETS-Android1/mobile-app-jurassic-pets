package com.example.mscproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_ITEM_TYPE = "ITEM_TYPE";
    public static final String COLUMN_ITEM_DESC = "ITEM_DESC";
    public static final String COLUMN_ITEM_COST = "ITEM_COST";
    public static final String COLUMN_ITEM_POINTS = "ITEM_POINTS";
    
    public static final String PET_TABLE = "PET_TABLE";
    public static final String COLUMN_PET_ID = "ID";
    public static final String COLUMN_PET_NAME = "PET_NAME";
    public static final String COLUMN_PET_TYPE = "PET_TYPE";
    public static final String COLUMN_PET_ITEMS = "PET_ITEMS";
    public static final String COLUMN_PET_COINS = "PET_COINS";
    public static final String COLUMN_PET_NUMSTEPS = "PET_NUMSTEPS";
    public static final String COLUMN_PET_LEVEL = "PET_LEVEL";
    public static final String COLUMN_PET_HAPPINESS = "PET_HAPPINESS";
    public static final String COLUMN_PET_ISAWAKE = "PET_ISAWAKE";


    public DatabaseHelper(@Nullable Context context) {
        super
                (context,
                "jurassicpets.db",
                null,
                1);
    }

    // This is called the first time a database is accessed.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createPetTable = "CREATE TABLE " + PET_TABLE + " (" + COLUMN_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PET_NAME + " TEXT, "
                + COLUMN_PET_TYPE + " TEXT,"
                + COLUMN_PET_ITEMS + " TEXT, "
                + COLUMN_PET_COINS + " INT, "
                + COLUMN_PET_NUMSTEPS + " INT,"
                + COLUMN_PET_LEVEL + " INT,"
                + COLUMN_PET_HAPPINESS + " INT,"
                + COLUMN_PET_ISAWAKE + " BOOLEAN)";

        sqLiteDatabase.execSQL(createPetTable);

        String createItemTable = "CREATE TABLE " + ITEM_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ITEM_NAME + " TEXT, "
                + COLUMN_ITEM_TYPE + " TEXT,"
                + COLUMN_ITEM_DESC + " TEXT, "
                + COLUMN_ITEM_COST + " INT, "
                + COLUMN_ITEM_POINTS + " INT)";

        sqLiteDatabase.execSQL(createItemTable);

    }

    /*
     This is called if the database version number changes.
     It prevents previous users apps from breaking when you change the database design.
    */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addPet(Pet myPet) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PET_NAME, myPet.getName());
        //values.put(DatabaseHelper.COLUMN_PET_TYPE, myPet.getType()); -- Need to change the type for enum
        values.put(COLUMN_PET_LEVEL, myPet.getLevel());
        values.put(COLUMN_PET_COINS, myPet.getCoins());
        values.put(COLUMN_PET_HAPPINESS, myPet.getHappiness());
        //values.put(DatabaseHelper.COLUMN_PET_ITEMS, myPet.getItems()); -- Need to change the type for array
        values.put(COLUMN_PET_ISAWAKE, myPet.getIsAwake());
        long insert = db.insert(PET_TABLE, null, values);
        //Positive number = Success, Negative number = fail
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

}
