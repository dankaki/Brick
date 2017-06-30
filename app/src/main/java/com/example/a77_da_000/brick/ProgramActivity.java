package com.example.a77_da_000.brick;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProgramActivity extends AppCompatActivity {
    public ArrayList<String> prognames = new ArrayList<>();
    public static final String PREFS_NAME = "MyPrefsFile";
    boolean[] tab = new boolean[100];
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences progdata = getSharedPreferences(PREFS_NAME, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView listView = (ListView) findViewById(R.id.ProgList);


        SharedPreferences.Editor editor = progdata.edit();
        for(i = 0; i<100; i++){
            tab[i] = progdata.getBoolean("tab"+i,false);
            if(tab[i]){
                prognames.add(progdata.getString("program"+i,""));
                editor.putInt("idOfNumber"+(prognames.size()-1),i);
            }
        }


        editor.apply();

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, prognames);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String[] actions = {"Edit name","Run","Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ProgramActivity.this);
                final int intid = (int)id;
                final EditText editname = new EditText(ProgramActivity.this);


                builder.setItems(actions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0:
                                AlertDialog.Builder editbuilder = new AlertDialog.Builder(ProgramActivity.this);
                                editbuilder.setTitle("Change name")
                                        .setView(editname)
                                    .setNegativeButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    String newname = editname.getText().toString();
                                                    prognames.set(intid,newname);
                                                    adapter.notifyDataSetChanged();
                                                    dialog.cancel();
                                                }
                                            });
                                AlertDialog editalert = editbuilder.create();
                                editalert.show();
                                dialog.cancel();
                                break;
                            case 2:
                                SharedPreferences codedata = getSharedPreferences(
                                        "CodeFile"+progdata.getInt("idOfNumber"+intid,intid),0);
                                SharedPreferences.Editor editor = progdata.edit();
                                SharedPreferences.Editor codeEditor = codedata.edit();

                                tab[progdata.getInt("idOfNumber"+intid,intid)] = false;
                                Set codeset = new HashSet();
                                codeEditor.putStringSet("codes",codeset);

                                for(i=intid; i<prognames.size()-1; i++){
                                    editor.putInt("idOfNumber"+i,progdata.getInt("idOfNumber"+(i+1),i));
                                }

                                editor.apply();
                                codeEditor.apply();

                                prognames.remove(intid);
                                adapter.notifyDataSetChanged();
                                dialog.cancel();
                                break;

                        }

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                SharedPreferences.Editor editor = progdata.edit();
                editor.putInt("id",progdata.getInt("idOfNumber"+position,position));
                editor.apply();
                Intent intent = new Intent(ProgramActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editname = new EditText(ProgramActivity.this);
                AlertDialog.Builder editbuilder = new AlertDialog.Builder(ProgramActivity.this);
                editbuilder.setTitle("New program")
                        .setView(editname)
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        for(i = 0; i<100; i++){
                                            if(!tab[i]){
                                                String name = editname.getText().toString();
                                                prognames.add(name);
                                                adapter.notifyDataSetChanged();
                                                SharedPreferences.Editor editor = progdata.edit();
                                                editor.putInt("idOfNumber"+(prognames.size()-1),i);
                                                editor.putString("program"+i,name);
                                                editor.apply();
                                                tab[i] = true;
                                                break;
                                            }
                                            if(i>=99 && tab[i]){
                                                Toast.makeText(getApplicationContext(),
                                                        "Not enough storage", Toast.LENGTH_SHORT).show();
                                            }
                                        }


                                        dialog.cancel();
                                    }
                                });
                AlertDialog editalert = editbuilder.create();
                editalert.show();
            }
        });
    }
    @Override
    protected void onStop(){
        super.onStop();
        final SharedPreferences progdata = getSharedPreferences(PREFS_NAME, 0);
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        for(i = 0; i<100 && prognames.size()>0; i++){
            editor.putBoolean("tab"+i,tab[i]);
        }
        for(i=0; i<prognames.size();i++){
            editor.putString("program"+progdata.getInt("idOfNumber"+i,0),prognames.get(i));
        }
        // Commit the edits!
        editor.apply();
    }

}
