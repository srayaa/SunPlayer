package com.player.sunplayer.e;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog;
import com.player.sunplayer.R;

/* compiled from: DialogUtils */
public class d {
    public static void a(Context context, int i, OnClickListener onClickListener) {
        AlertDialog.Builder aVar = new AlertDialog.Builder(context);
        aVar.setTitle(i);
        aVar.setPositiveButton((int) R.string.confirm, onClickListener);
        aVar.setNegativeButton((int) R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        aVar.create();
    }
}
