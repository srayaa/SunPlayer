package com.player.sunplayer.a;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.player.sunplayer.R;
import com.player.sunplayer.e.f;
import java.io.File;
import java.util.List;

/* compiled from: FileAdapter */
public class FileAdapter extends android.support.v7.widget.RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context a;
    List<File> b;
    b c;
    File d;
    private LayoutInflater e;

    /* compiled from: FileAdapter */
    class a extends RecyclerView.ViewHolder {
        TextView a;

        public a(View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tv_file_name);
        }
    }

    /* compiled from: FileAdapter */
    public interface b {
        void a(View view, int i);
    }

    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder vVar, int i) {
        a((a) vVar, i);
    }

    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public void a(b bVar) {
        this.c = bVar;
    }

    public FileAdapter(Context context, File file) {
        this.a = context;
        this.b = f.a(file.listFiles());
        f.a(this.b);
        this.e = LayoutInflater.from(context);
        this.d = file;
    }

    public RecyclerView.ViewHolder a(ViewGroup viewGroup, int i) {
        return new a(this.e.inflate(R.layout.file_list, null));
    }

    public void a(a aVar, final int i) {
        aVar.a.setText(((File) this.b.get(i)).getName());
        aVar.a.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (FileAdapter.this.c != null) {
                    FileAdapter.this.c.a(view, i);
                }
            }
        });
        File file = (File) this.b.get(i);
        if (file.isDirectory()) {
            aVar.a.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_file_dir, 0, 0, 0);
        } else if (f.a(file)) {
            aVar.a.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_file_video, 0, 0, 0);
        } else if (f.e(file)) {
            aVar.a.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_file_audio, 0, 0, 0);
        } else if (f.d(file)) {
            aVar.a.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_file_text, 0, 0, 0);
        } else if (f.c(file)) {
            aVar.a.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_file_picture, 0, 0, 0);
        } else if (f.f(file)) {
            aVar.a.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_file_bt, 0, 0, 0);
        } else {
            aVar.a.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_file_other, 0, 0, 0);
        }
    }

    public int getItemCount() {
        return this.b.size();
    }

    public void a(File file) {
        this.d = file;
        this.b = f.a(file.listFiles());
        f.a(this.b);
    }
}
