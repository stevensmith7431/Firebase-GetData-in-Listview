package com.example.firebasefetchdatalistview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyAdapter extends ArrayAdapter<MyList> {

    private Activity context;
    private List<MyList> list;

    public MyAdapter(Activity context,List<MyList> list) {
        super(context,R.layout.list_items,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position,@Nullable View contentView,@Nullable ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listitem = inflater.inflate(R.layout.list_items,null,true);

        TextView textView1 = listitem.findViewById(R.id.text1id);
        TextView textView2 = listitem.findViewById(R.id.text2id);
        TextView textView3 = listitem.findViewById(R.id.text3id);

        MyList myList = list.get(position);

        textView1.setText(myList.getName());
        textView2.setText(myList.getCity());
        textView3.setText(myList.getGender());
        return listitem;
    }
}
