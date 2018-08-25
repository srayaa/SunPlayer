package com.player.sunplayer.e;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;

/* compiled from: ContentUriUtils */
public class b {
    public static String a(Context context, Uri uri) {
        Uri uri2 = null;
        if ((VERSION.SDK_INT >= 19 ? 1 : null) == null || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (d(uri)) {
                    return uri.getLastPathSegment();
                }
                return a(context, uri, null, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } else if (a(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Environment.getExternalStorageDirectory());
                stringBuilder.append("/");
                stringBuilder.append(split[1]);
                return stringBuilder.toString();
            }
        } else if (b(uri)) {
            return a(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else if (c(uri)) {
            Object obj = DocumentsContract.getDocumentId(uri).split(":")[0];
            if ("image".equals(obj)) {
                uri2 = Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(obj)) {
                uri2 = Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(obj)) {
                uri2 = Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return a(context, uri2, "_id=?", new String[]{DocumentsContract.getDocumentId(uri).split(":")[1]});
        }
        return null;
    }

    public static String a(Context context, Uri uri, String str, String[] strArr) {
        Throwable th;
        Cursor query;
        try {
            query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        int columnIndex = query.getColumnIndex("_data");
                        String str2;
                        if (columnIndex == -1) {
                            str2 = "";
                            if (query != null) {
                                query.close();
                            }
                            return str2;
                        }
                        str2 = query.getString(columnIndex);
                        if (query != null) {
                            query.close();
                        }
                        return str2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th3.printStackTrace();
            query = null;
            if (query != null) {
                query.close();
            }
        }
        return null;
    }

    public static boolean a(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean b(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean c(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean d(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
