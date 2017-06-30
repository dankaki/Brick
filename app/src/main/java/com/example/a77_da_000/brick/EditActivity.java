package com.example.a77_da_000.brick;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class EditActivity extends AppCompatActivity {

    public ArrayList<String> codes = new ArrayList<String>();
    public static final String PREFS_NAME = "MyPrefsFile";
    String CODE_PREFS_NAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView listView = (ListView) findViewById(R.id.listView);
        SharedPreferences progdata = getSharedPreferences(PREFS_NAME, 0);
        Set codeset = new HashSet(codes);
        int codeID = progdata.getInt("id",0);
        CODE_PREFS_NAME = "CodeFile"+ codeID;
        SharedPreferences codedata = getSharedPreferences(CODE_PREFS_NAME,0);
        codeset = codedata.getStringSet("codes",codeset);
        List temp = new ArrayList(codeset);
        codes = new ArrayList(temp);
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,codes);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] blocks = {"Action","If","Delay","Loop"};
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setTitle("Choose type")
                        .setItems(blocks, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int item){
                                switch(item){
                                    case 0:     //Action
                                        codes.add("Action"+CODE_PREFS_NAME);
                                        adapter.notifyDataSetChanged();
                                        break;
                                    case 1:     //if
                                        codes.add("If _ then _");
                                        adapter.notifyDataSetChanged();
                                        break;
                                    case 2:     //Delay
                                        codes.add("Delay _ ms");
                                        adapter.notifyDataSetChanged();
                                        break;
                                    case 3:     //Loop
                                        codes.add("Loop");
                                        adapter.notifyDataSetChanged();
                                        break;

                                }
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onStop(){
        super.onStop();
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences codepref = getSharedPreferences(CODE_PREFS_NAME, 0);
        SharedPreferences.Editor editor = codepref.edit();
        Set codeset = new HashSet(codes);
        editor.putStringSet("codes",codeset);

        // Commit the edits!
        editor.apply();
    }

}
