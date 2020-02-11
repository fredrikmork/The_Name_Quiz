package com.example.namequizapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomListView extends ArrayAdapter<Person> {

    private ArrayList<Person> p;
    private Activity context;

    public CustomListView(Activity context, ArrayList<Person> p) {
        super(context, R.layout.listview_layout, p);
        this.context = context;
        this.p = p;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;

        final PersonDao personDao = MainActivity.quizRoomDatabase.personDAO();

        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) r.getTag();

        }
        //viewHolder.ivw.setImageBitmap(p.get(position).getBitmap());
        //viewHolder.tvw.setText(p.get(position).getName());

        final ArrayList<Person> personer = (ArrayList<Person>) personDao.loadAllPersons();

        viewHolder.tvw.setText(personer.get(position).getName());
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personDao.deletePerson(personer.get(position));
                p.remove(position);
                notifyDataSetChanged();
            }
        });

        return r;
    }


    class ViewHolder {

        private TextView tvw;
        private ImageView ivw;
        private ImageButton btn;

        public ViewHolder(View v){
            tvw = v.findViewById(R.id.textView);
            ivw = v.findViewById(R.id.imageView);
            btn = v.findViewById(R.id.deleteButton);
        }

    }
}
