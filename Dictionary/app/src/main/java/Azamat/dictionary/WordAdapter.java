package Azamat.dictionary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cursoradapter.widget.CursorAdapter;

public class WordAdapter extends CursorAdapter {

    public WordAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        TextView word = view.findViewById(R.id.word);
        TextView translate = view.findViewById(R.id.translate);

        word.setText(cursor.getString(1));
        translate.setText(cursor.getString(2));


    }
}
