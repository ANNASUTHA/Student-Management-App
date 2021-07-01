package Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.annasutha.student_app.R;

import java.util.Objects;

import DataBase.DatabaseHelper;
import Models.User;

public class Dashboard_Activity extends AppCompatActivity {
    private static final String KEY_USERNAME = "";
    private static final String KEY_PASSWORD = "";
    private DatabaseHelper databaseHelper;
    private SharedPreferences sharedPreferences;
    private User user;
    private String phone, password;
    private CardView addStudentCardView, viewStudentsCardView, mapViewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Students Dashboard ");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FF6200EE"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        //login();
        initViews();
        //initListeners();
        addStudentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addStudent = new Intent(getApplicationContext(), AddStudentsActivity.class);
                startActivity(addStudent);
            }
        });
        viewStudentsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewStudent = new Intent(getApplicationContext(), StudentsList_Activity.class);
                startActivity(viewStudent);
            }
        });
        mapViewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapView = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapView);
            }
        });
    }
    /*private void initListeners() {
        addStudentCardView.setOnClickListener(this::onClick);
        viewStudentsCardView.setOnClickListener(this::onClick);
        mapViewCard.setOnClickListener(this::onClick);
    }*/

    /*public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addStudentsCard:
                Intent addStudent = new Intent(getApplicationContext(), AddStudentsActivity.class);
                startActivity(addStudent);
                break;
            case R.id.viewStudentsCard:
                Intent viewStudent = new Intent(getApplicationContext(), StudentsList_Activity.class);
                startActivity(viewStudent);
                break;
            case R.id.MapViewCard:
                Intent mapView = new Intent(getApplicationContext(), MapsViewActivity.class);
                startActivity(mapView);
                break;
        }
    }*/
    private void initViews() {
        addStudentCardView = findViewById(R.id.addStudentsCard);
        viewStudentsCardView = findViewById(R.id.viewStudentsCard);
        mapViewCard = findViewById(R.id.MapViewCard);
    }


    private void login() {
        databaseHelper.checkUser(phone, password);
        phone = user.getPhone();
        password = user.getPassword();
        /*phone = textInputEditTextPhoneTV.getText().toString();
        password = textInputEditTextPassword.getText().toString();*/
        sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, phone);
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
        /*if(phone.equals(user.getPhone()) && password.equals(user.getPassword())){
            startActivity(new Intent(Dashboard_Activity.this, Dashboard_Activity.class));
        }*/
    }

}