package com.example.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bai27.R;
import com.example.model.Album;

import java.util.List;

public class AlbumAdapter extends ArrayAdapter<Album> {
    Activity context;
    int resource;
    List<Album> objects;
    public AlbumAdapter(Activity context, int resource, List<Album> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects=objects;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        TextView txtStt = row.<TextView>findViewById(R.id.txtStt);
        TextView txtMa = row.<TextView>findViewById(R.id.txtMa);
        TextView txtTen = row.<TextView>findViewById(R.id.txtTen);

        Album album = this.objects.get(position);
        txtStt.setText(position+1+"");
        txtMa.setText(album.getMa());
        txtTen.setText(album.getTen());
        return row;
    }
}
