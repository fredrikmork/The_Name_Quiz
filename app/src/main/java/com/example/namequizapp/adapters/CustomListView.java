package com.example.namequizapp.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.namequizapp.models.Person;
import com.example.namequizapp.interfaces.PersonDao;
import com.example.namequizapp.R;
import com.example.namequizapp.activities.MainActivity;

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

    public Bitmap convertToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        final PersonDao personDao = MainActivity.quizRoomDatabase.personDAO();
        final ArrayList<Person> persons = (ArrayList<Person>) personDao.loadAllPersons();
        byte[] bytes = persons.get(position).getImage();
        Bitmap bitmap = convertToBitmap(bytes);

        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) r.getTag();

        }

        viewHolder.ivw.setImageBitmap(bitmap);
        viewHolder.tvw.setText(persons.get(position).getName());
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personDao.deletePerson(persons.get(position));
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

        public ViewHolder(View v) {
            tvw = v.findViewById(R.id.textView);
            ivw = v.findViewById(R.id.imageView);
            btn = v.findViewById(R.id.deleteButton);
        }

    }
}
