package Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.annasutha.student_app.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AddStudentsActivity extends AppCompatActivity {
    private TextInputEditText textInputEditTextName, textInputEditTextSchool,
            textInputEditTextPhone, textInputEditTextFatherName,
            textInputEditTextMomName, textInputEditTextAddress, textInputEditTextCity;
    private TextInputLayout textInputLayoutName, textInputLayoutSchool,
            textInputLayoutPhone, textInputLayoutFatherName,
            textInputLayoutMomName, textInputLayoutAddress, textInputLayoutCity;
    private AppCompatTextView locationTV;
    private AppCompatButton submitButton;
    private ImageView profile;
    private ConstraintLayout signInConstraintLT;
    private RadioButton male, female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Add Students Details");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FF6200EE"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        initViews();
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(profile);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(signInConstraintLT, getString(R.string.saved), Snackbar.LENGTH_LONG).show();
                Intent submit = new Intent(getApplicationContext(), Dashboard_Activity.class);
                startActivity(submit);

            }
        });
        locationTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapView = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapView);
            }
        });

    }

    private void initViews() {
        signInConstraintLT = findViewById(R.id.constraintLayoutStu);
        textInputEditTextName = findViewById(R.id.textInputEditTextName);
        textInputEditTextSchool = findViewById(R.id.textInputEditTextSchool);
        textInputEditTextPhone = findViewById(R.id.textInputEditTextPhone);
        textInputEditTextFatherName = findViewById(R.id.textInputEditTextFatherName);
        textInputEditTextMomName = findViewById(R.id.textInputEditTextMomName);
        textInputEditTextAddress = findViewById(R.id.textInputEditTextAddress);
        textInputEditTextCity = findViewById(R.id.textInputEditTextCity);
        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputLayoutSchool = findViewById(R.id.textInputLayoutSchool);
        textInputLayoutPhone = findViewById(R.id.textInputLayoutPhone);
        textInputLayoutFatherName = findViewById(R.id.textInputLayoutFatherName);
        textInputLayoutMomName = findViewById(R.id.textInputLayoutMomName);
        textInputLayoutCity = findViewById(R.id.textInputLayoutCity);
        locationTV = findViewById(R.id.location);
        submitButton = findViewById(R.id.submit);
        profile = findViewById(R.id.profile);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
    }
}