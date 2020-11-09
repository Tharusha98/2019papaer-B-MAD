package com.example.a2019papaer2.DataBase;


import android.provider.BaseColumns;

public final class DataBaseMaster {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private DataBaseMaster() {}

        /* Inner class that defines the table contents */
        public static class Users implements BaseColumns {
            public static final String TABLE_NAME = "users";
            public static final String COLUMN_1 = "username";
            public static final String COLUMN_2 = "password";
            public static final String COLUMN_3= "usertype";
        }

    public static class Games implements BaseColumns {
        public static final String TABLE_NAME = "games";
        public static final String COLUMN_1 = "gamename";
        public static final String COLUMN_2 = "gameyear";
    }

    public static class Comments implements BaseColumns {
        public static final String TABLE_NAME = "comments";
        public static final String COLUMN_1 = "gamename";
        public static final String COLUMN_2 = "gamerating";
        public static final String COLUMN_3 = "gamecomments";
    }




    }

