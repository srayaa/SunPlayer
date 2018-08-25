package com.player.sunplayer.filePicker.a;

import android.content.Context;
import android.content.CursorLoader;
import android.os.Bundle;
import android.provider.MediaStore.Files;

/* compiled from: PhotoDirectoryLoader */
public class PhotoDirectoryLoader extends CursorLoader {
    final String[] a = new String[]{"_id", "_data", "bucket_id", "bucket_display_name", "date_added", "title"};

    public PhotoDirectoryLoader(Context context, Bundle bundle) {
        super(context);
        String string = bundle.getString("EXTRA_BUCKET_ID", null);
        int i = bundle.getInt("EXTRA_FILE_TYPE", 1);
        setProjection(null);
        setUri(Files.getContentUri("external"));
        setSortOrder("_id DESC");
        String str = "media_type=1";
        if (i == 3) {
            str = "media_type=3";
        }
        if (string != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" AND bucket_id='");
            stringBuilder.append(string);
            stringBuilder.append("'");
            str = stringBuilder.toString();
        }
        setSelection(str);
    }
}
