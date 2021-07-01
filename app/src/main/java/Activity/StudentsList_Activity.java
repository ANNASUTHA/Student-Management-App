package Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.annasutha.student_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Adapters.studentsRecyclerAdapter;
import DataBase.DatabaseHelper;
import Models.User;

public class StudentsList_Activity extends AppCompatActivity {
    private final AppCompatActivity activity = StudentsList_Activity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<User> listStudents;
    private studentsRecyclerAdapter studentsRecyclerAdapterStr;
    public DatabaseHelper databaseHelper;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        Objects.requireNonNull(getSupportActionBar()).setTitle("View Students");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FF6200EE"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        initViews();
        initObjects();
    }

    private void initViews() {
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
    }

    private void initObjects() {
        listStudents = new ArrayList<>();
        studentsRecyclerAdapterStr = new studentsRecyclerAdapter(listStudents);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(studentsRecyclerAdapterStr);
        databaseHelper = new DatabaseHelper(activity);
        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        //textViewName.setText(emailFromIntent);
        getDataFromSQLite();
    }

    public void getDataFromSQLite() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listStudents.clear();
                listStudents.addAll(databaseHelper.getAllUser());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                studentsRecyclerAdapterStr.notifyDataSetChanged();
            }
        }.execute();
    }
}