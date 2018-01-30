package com.example.bussysteemgogent.persistency;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by prebe on 30/01/2018.
 */

public class BusContract {

    //EERST schema aanmaken die database gaat declareren
    //added voor contentprovider
    public static final String CONTENT_AUTHORITY = "com.example.provider.bussysteemgogent";

    //added voor contentprovider
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //added voor contentprovider
    // Path to get CLIENT APP to our table
    public static final String PATH_BUSSEN = "bussen";

    public static final class BusEntry implements BaseColumns {

        //added voor contentprovider
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BUSSEN);

        // Table Name
        public static final String TABLE_NAME = "bussen";

        // Columns
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NUMMERPLAAT = "nummerplaat";
    }


}
