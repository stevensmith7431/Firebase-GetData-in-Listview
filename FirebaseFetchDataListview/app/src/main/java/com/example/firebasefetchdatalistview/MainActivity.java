package com.example.firebasefetchdatalistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name,city;
    Spinner gender;
    Button save;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapters;
    private DatabaseReference databaseReference;

    private List<MyList> mProductlist;
    private MyAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.edit1id);
        city = findViewById(R.id.edit2id);
        save = findViewById(R.id.saveid);
        gender = findViewById(R.id.spinnerid);
        listView = findViewById(R.id.listviewid);

        mProductlist = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Developers");

        arrayList = new ArrayList<String>();
        arrayList.add("Select Gender");
        arrayList.add("Male");
        arrayList.add("Female");
        arrayList.add("Transgender");
        adapters = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
        gender.setAdapter(adapters);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InsertData();
            }
        });
    }

    private void InsertData(){

        String s_name = name.getText().toString();
        String s_city = city.getText().toString();
        String s_gender = gender.getSelectedItem().toString();

        if (TextUtils.isEmpty(s_name) || TextUtils.isEmpty(s_city) || s_gender.contains("Select Gender")){

            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();

        }
        else {

            String id = databaseReference.push().getKey();

            MyList myList = new MyList(id,s_name,s_city,s_gender);

            databaseReference.child(id).setValue(myList)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                            else {

                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mProductlist.clear();

                for (DataSnapshot devshot : dataSnapshot.getChildren()){

                    MyList myList = devshot.getValue(MyList.class);

                    mProductlist.add(myList);
                }

                adapter = new MyAdapter(MainActivity.this,mProductlist);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
