package com.example.goldstein.myapplication.Contracts;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Goldstein on 18/04/2016.
 */
public class CameraInfoContract {

    public static final String CONTENT_AUTHORITY = "com.example.goldstein.myapplication.Providers.CameraProvider";
    public static final String PATH_CAMERA = "camera";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CAMERA).build();

    public static class CameraInfoEntry implements BaseColumns
    {
        public static final String TABLE_NAME ="CameraInfo";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_content = "content";
        public static final String COLUMN_imageName = "imageName";
        public static final String COLUMN_ID= "_id";
    }
}
