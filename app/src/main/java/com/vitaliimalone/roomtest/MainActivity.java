package com.vitaliimalone.roomtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vitaliimalone.roomtest.model.AppDatabase;
import com.vitaliimalone.roomtest.model.Person;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PersonAdapter.OnItemClickListener{

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.add_button)
    Button addButton;

    @BindView(R.id.delete_all_button)
    Button deleteAllButton;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    int id;
    List<Person> people;
    AppDatabase db;
    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        db = AppDatabase.getAppDatabase(this);
        people = db.personDao().getAllPeople();

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        adapter = new PersonAdapter(people, this);
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: add new person " + id);
                db.personDao().insertPeron(new Person("Test" + id, "Test2"));
                id++;
                updateUI();

            }
        });

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: delete all");
                db.personDao().deleteAll();
                updateUI();
            }
        });
    }

    private void updateUI() {
        people = db.personDao().getAllPeople();
        adapter.updateList(people);
    }

    @Override
    public void onItemClick(Person person, int position) {
        Toast.makeText(this, "You clicked on: #" + position,Toast.LENGTH_SHORT).show();
    }
}
