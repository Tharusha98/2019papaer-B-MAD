package com.example.a2019papaer2.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;


public class DBhandler  extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public DBhandler (Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES_Users);
            db.execSQL(SQL_CREATE_ENTRIES_Game);
            db.execSQL(SQL_CREATE_ENTRIES_Comments);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES_Users);
            db.execSQL(SQL_DELETE_ENTRIES_Games);
            db.execSQL(SQL_DELETE_ENTRIES_Comments);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        private static final String SQL_CREATE_ENTRIES_Users =
                "CREATE TABLE " + DataBaseMaster.Users.TABLE_NAME + " (" +
                        DataBaseMaster.Users._ID + " INTEGER PRIMARY KEY," +
                        DataBaseMaster.Users.COLUMN_1 + " TEXT," +
                        DataBaseMaster.Users.COLUMN_2 + " TEXT," +
                        DataBaseMaster.Users.COLUMN_3 + " TEXT)";

        private static final String SQL_DELETE_ENTRIES_Users =
                "DROP TABLE IF EXISTS " + DataBaseMaster.Users.TABLE_NAME;




        private static final String SQL_CREATE_ENTRIES_Game =
                "CREATE TABLE " + DataBaseMaster.Games.TABLE_NAME + " (" +
                        DataBaseMaster.Games._ID + " INTEGER PRIMARY KEY," +
                        DataBaseMaster.Games.COLUMN_1 + " TEXT," +
                        DataBaseMaster.Games.COLUMN_2 + " TEXT)";

        private static final String SQL_DELETE_ENTRIES_Games =
                "DROP TABLE IF EXISTS " + DataBaseMaster.Games.TABLE_NAME;




        private static final String SQL_CREATE_ENTRIES_Comments =
                "CREATE TABLE " + DataBaseMaster.Comments.TABLE_NAME + " (" +
                        DataBaseMaster.Comments._ID + " INTEGER PRIMARY KEY," +
                        DataBaseMaster.Comments.COLUMN_1 + " TEXT," +
                        DataBaseMaster.Comments.COLUMN_2 + " INTEGER," +
                        DataBaseMaster.Comments.COLUMN_3 + " TEXT)" ;

        private static final String SQL_DELETE_ENTRIES_Comments =
                "DROP TABLE IF EXISTS " + DataBaseMaster.Comments.TABLE_NAME;


        public boolean registeruser(String uname,String pwd,String type){



            // Gets the data repository in write mode
            SQLiteDatabase db =  getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(DataBaseMaster.Users.COLUMN_1, uname);
            values.put(DataBaseMaster.Users.COLUMN_2, pwd);
            values.put(DataBaseMaster.Users.COLUMN_3, type);



            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(DataBaseMaster.Users.TABLE_NAME, null, values);

            if(newRowId >= 1){
                return true;
            }else{
                return false;
            }
        }


        public boolean loginuser(String uname,String pwd){

            SQLiteDatabase db = getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    BaseColumns._ID,
                   DataBaseMaster.Users.COLUMN_1,

            };

            // Filter results WHERE "title" = 'My Title'
            String selection = DataBaseMaster.Users.COLUMN_1 + " = ? AND " + DataBaseMaster.Users.COLUMN_2 + "= ? ";
            String[] selectionArgs = { uname,pwd };

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                  DataBaseMaster.Users.COLUMN_1 + " DESC";

            Cursor cursor = db.query(
                    DataBaseMaster.Users.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );

           ArrayList itemIds = new ArrayList<>();
            while(cursor.moveToNext()) {
                String itemId = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseMaster.Users.COLUMN_1));
                itemIds.add(itemId);
            }
            cursor.close();

            if(itemIds.isEmpty()){
                return false;
            }else{
                return true;
            }

        }



        public boolean addGame(String name,String year){

            // Gets the data repository in write mode
            SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(DataBaseMaster.Games.COLUMN_1, name);
            values.put(DataBaseMaster.Games.COLUMN_2, year);

// Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(DataBaseMaster.Games.TABLE_NAME, null, values);

            if(newRowId >=1){
                return true;
            }else{
                return false;
            }
        }

    public ArrayList Viewgames(){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DataBaseMaster.Games.COLUMN_1,

        };

        Cursor cursor = db.query(
                DataBaseMaster.Games.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null              // The sort order
        );

        ArrayList itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemId = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseMaster.Games.COLUMN_1));
            itemIds.add(itemId);
        }
        cursor.close();
        return itemIds;

    }

    public  boolean insertComments(String name,String rating,String comments){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

            // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DataBaseMaster.Comments.COLUMN_1, name);
        values.put(DataBaseMaster.Comments.COLUMN_2, rating);
        values.put(DataBaseMaster.Comments.COLUMN_3, comments);

            // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DataBaseMaster.Comments.TABLE_NAME, null, values);

        if(newRowId >=1){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList viewcomments(String name){
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DataBaseMaster.Comments.COLUMN_3,

        };

// Filter results WHERE "title" = 'My Title'
        String selection = DataBaseMaster.Comments.COLUMN_1 + " = ?";
        String[] selectionArgs = { name };

// How you want the results sorted in the resulting Cursor

        Cursor cursor = db.query(
                DataBaseMaster.Comments.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
               null               // The sort order
        );

        ArrayList itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemId = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseMaster.Comments.COLUMN_3));
            itemIds.add(itemId);
        }
        cursor.close();
        return itemIds;
    }

    public ArrayList getratings(String name){

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
               DataBaseMaster.Comments.COLUMN_2,

        };

// Filter results WHERE "title" = 'My Title'
        String selection = DataBaseMaster.Comments.COLUMN_1 + " = ?";
        String[] selectionArgs = { name };

// How you want the results sorted in the resulting Cursor

        Cursor cursor = db.query(
             DataBaseMaster.Comments.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
               null               // The sort order
        );

       ArrayList itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
        String itemId = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseMaster.Comments.COLUMN_2));
            itemIds.add(itemId);
        }
        cursor.close();

        return itemIds;
    }























    }

