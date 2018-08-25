package com.player.sunplayer.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.player.sunplayer.R;
import com.player.sunplayer.bean.History;
import com.player.sunplayer.bean.PlayHistory;
import java.util.List;

/* compiled from: PlayerHistoryAdapter */
public class PlayerHistoryAdapter extends BaseAdapter {
    private List<History> a;
    private Context b;
    private LayoutInflater c;

    /* compiled from: PlayerHistoryAdapter */
    static class a {
        TextView a;

        private a(View view) {
            this.a = (TextView) view.findViewById(R.id.tv_video_name);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public PlayerHistoryAdapter(Context context, List<History> list) {
        this.a = list;
        this.b = context;
        this.c = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.c.inflate(R.layout.list_history, viewGroup, false);
        }
        a(view).a.setText(((History) this.a.get(i)).getTitle());
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

    public void a(int i) {
        new PlayHistory(this.b).delete((History) this.a.get(i));
        this.a.remove(i);
        notifyDataSetChanged();
    }

    public void a() {
        this.a.clear();
        notifyDataSetChanged();
    }
}
