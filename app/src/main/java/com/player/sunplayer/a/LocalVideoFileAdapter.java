package com.player.sunplayer.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.player.sunplayer.R;
import com.player.sunplayer.filePicker.models.MediaDirectory;
import java.util.List;

/* compiled from: LocalVideoFileAdapter */
public class LocalVideoFileAdapter extends BaseAdapter {
    a a;
    private Context b;
    private LayoutInflater c;
    private List<MediaDirectory> d;

    /* compiled from: LocalVideoFileAdapter */
    class a {
        TextView a;
        ImageView b;
        TextView c;

        public a(View view) {
            this.b = (ImageView) view.findViewById(R.id.iv_dir_icon);
            this.c = (TextView) view.findViewById(R.id.tv_dir_name);
            this.a = (TextView) view.findViewById(R.id.tv_dir_num);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public LocalVideoFileAdapter(Context context, List<MediaDirectory> list) {
        this.d = list;
        this.b = context;
        this.c = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.d.size();
    }

    public Object getItem(int i) {
        return this.d.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.c.inflate(R.layout.list_dir, null);
        }
        this.a = a(view);
        MediaDirectory mediaDirectory = (MediaDirectory) this.d.get(i);
        this.a.c.setText(mediaDirectory.a());
        TextView textView = this.a.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mediaDirectory.b().size());
        stringBuilder.append(this.b.getString(R.string.video_num));
        textView.setText(stringBuilder.toString());
        return view;
    }

    private a a(View view) {
        a aVar = (a) view.getTag();
        if (aVar != null) {
            return aVar;
        }
        aVar = new a(view);
        view.setTag(aVar);
        return aVar;
    }

    public void a(List<MediaDirectory> list) {
        this.d = list;
        notifyDataSetChanged();
    }
}
