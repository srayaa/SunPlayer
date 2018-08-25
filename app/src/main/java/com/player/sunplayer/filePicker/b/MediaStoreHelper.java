package com.player.sunplayer.filePicker.b;

import android.app.Activity;
import android.os.Bundle;
import com.player.sunplayer.filePicker.models.MediaDirectory;
import droidninja.filepicker.cursors.loadercallbacks.FileResultCallback;

/* compiled from: MediaStoreHelper */
public class MediaStoreHelper {
    public static void a(Activity activity, Bundle bundle, FileResultCallback<MediaDirectory> bVar) {
        if (activity.getLoaderManager().getLoader(3) != null) {
            activity.getLoaderManager().restartLoader(3, bundle, new com.player.sunplayer.filePicker.a.a.PhotoDirLoaderCallbacks(activity, bVar));
        } else {
            activity.getLoaderManager().initLoader(3, bundle, new com.player.sunplayer.filePicker.a.a.PhotoDirLoaderCallbacks(activity, bVar));
        }
    }
}
