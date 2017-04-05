package com.example.akhilmg.samplepreferenceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.akhilmg.samplepreferenceapp.util.SamplePreference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> subjectList;
    private SamplePreference pref;

    private EditText subject;
    private Button save;
    private Button clearList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subject = (EditText) findViewById(R.id.ed_subject);
        save = (Button) findViewById(R.id.btn_save);
        clearList = (Button) findViewById(R.id.btn_clear);
        listView = (ListView) findViewById(R.id.subject_list);

        pref = new SamplePreference(this);

        subjectList = pref.getSubjectList();
        if (subjectList == null) {
            subjectList = new ArrayList<>();
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                subjectList);

        listView.setAdapter(arrayAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!subject.getText().toString().trim().equals("")) {
                    subjectList.add(subject.getText().toString());
                    pref.setSubjectList(subjectList);
                    arrayAdapter.notifyDataSetChanged();//To refresh list
                    Toast.makeText(MainActivity.this, "Data saved...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectList.clear();
                pref.setSubjectList(subjectList);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}
