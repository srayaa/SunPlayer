package com.player.sunplayer.a;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.player.sunplayer.R;
import com.player.sunplayer.e.n;
import com.player.sunplayer.filePicker.models.Media;
import java.io.File;
import java.util.List;

/* compiled from: LocalVideoAdapter */
public class LocalVideoAdapter extends BaseAdapter {
    a a;
    private Context b;
    private LayoutInflater c;
    private List<Media> d;

    /* compiled from: LocalVideoAdapter */
    class a {
        TextView a;
        ImageView b;
        TextView c;

        public a(View view) {
            this.c = (TextView) view.findViewById(R.id.tv_Video_info);
            this.a = (TextView) view.findViewById(R.id.tv_Video_name);
            this.b = (ImageView) view.findViewById(R.id.iv_local_thu);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public LocalVideoAdapter(Context context, List<Media> list) {
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
            view = this.c.inflate(R.layout.listview_file, null);
        }
        this.a = a(view);
        Media media = (Media) this.d.get(i);
        this.a.a.getPaint().setFakeBoldText(true);
        this.a.a.setText(media.e());
        TextView textView = this.a.c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(n.a(media.b()));
        stringBuilder.append(File.separator);
        stringBuilder.append(com.player.sunplayer.e.f.a(media.a()));
        textView.setText(stringBuilder.toString());
        Glide.with(this.b).load(Uri.fromFile(new File(media.c()))).into(this.a.b);
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
}
