package com.player.sunplayer.filePicker.a.a;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.player.sunplayer.filePicker.a.PhotoDirectoryLoader;
import com.player.sunplayer.filePicker.models.MediaDirectory;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.PickerManager;
import droidninja.filepicker.cursors.loadercallbacks.FileResultCallback;

/* compiled from: PhotoDirLoaderCallbacks */
public class PhotoDirLoaderCallbacks implements LoaderCallbacks<Cursor> {
    private WeakReference<Context> a;
    private FileResultCallback<MediaDirectory> b;

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public PhotoDirLoaderCallbacks(Context context, FileResultCallback<MediaDirectory> bVar) {
        this.a = new WeakReference(context);
        this.b = bVar;
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new PhotoDirectoryLoader((Context) this.a.get(), bundle);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        a(loader, (Cursor) data);
    }

    public void a(Loader<Cursor> loader, Cursor cursor) {
        PhotoDirLoaderCallbacks aVar = this;
        Cursor cursor2 = cursor;
        if (cursor2 != null) {
            List<MediaDirectory> arrayList = new ArrayList();
            MediaDirectory mediaDirectory = new MediaDirectory();
            while (cursor.moveToNext()) {
                int i = cursor2.getInt(cursor2.getColumnIndexOrThrow("_id"));
                String string = cursor2.getString(cursor2.getColumnIndexOrThrow("bucket_id"));
                String string2 = cursor2.getString(cursor2.getColumnIndexOrThrow("bucket_display_name"));
                String string3 = cursor2.getString(cursor2.getColumnIndexOrThrow("_data"));
                String string4 = cursor2.getString(cursor2.getColumnIndexOrThrow("title"));
                long j = cursor2.getLong(cursor2.getColumnIndexOrThrow("duration"));
                long j2 = cursor2.getLong(cursor2.getColumnIndexOrThrow("_size"));
                int i2 = cursor2.getInt(cursor2.getColumnIndexOrThrow("media_type"));
                MediaDirectory mediaDirectory2 = new MediaDirectory();
                mediaDirectory2.b(string);
                mediaDirectory2.a(string2);
                mediaDirectory.a(i, string4, string3, i2, j2, j);
                if (arrayList.contains(mediaDirectory2)) {
                    ((MediaDirectory) arrayList.get(arrayList.indexOf(mediaDirectory2))).a(i, string4, string3, i2, j2, j);
                } else {
                    MediaDirectory mediaDirectory3;
                    if (!string3.toLowerCase().endsWith("gif")) {
                        mediaDirectory3 = mediaDirectory2;
                        mediaDirectory2.a(i, string4, string3, i2, j2, j);
                    } else if (PickerManager.getInstance().isShowGif()) {
                        mediaDirectory3 = mediaDirectory2;
                        mediaDirectory2.a(i, string4, string3, i2, j2, j);
                    } else {
                        mediaDirectory3 = mediaDirectory2;
                    }
                    mediaDirectory3.a(cursor2.getLong(cursor2.getColumnIndexOrThrow("date_added")));
                    arrayList.add(mediaDirectory3);
                }
            }
            if (aVar.b != null) {
                arrayList.add(0, mediaDirectory);
                aVar.b.onResultCallback(arrayList);
            }
        }
    }
}
