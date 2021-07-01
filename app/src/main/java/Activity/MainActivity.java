package Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.annasutha.student_app.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import DataBase.DatabaseHelper;
import Models.InputValidation;
import Models.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String KEY_USERNAME = "";
    private static final String KEY_PASSWORD = "";
    private final AppCompatActivity activity = MainActivity.this;
    private ConstraintLayout constraintLayout;
    private TextInputLayout textInputLayoutPhone;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextPhoneTV;
    private TextInputEditText textInputEditTextPassword;
    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView textViewLinkRegister;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private SharedPreferences sharedPreferences;
    private User user;
    private String phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        sharedPreferences = this.getSharedPreferences("Login", MODE_PRIVATE);
        phone = sharedPreferences.getString(KEY_USERNAME, null);
        password = sharedPreferences.getString(KEY_PASSWORD, null);
       /* if (phone == null && password == null){
            login();
        }*/
       /* if(phone.equals(user.getPhone()) && password.equals(user.getPassword())){
            login();
        }*/

        initViews();
        initListeners();
        initObjects();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), SignIn_Activity.class);
                startActivity(intentRegister);
                break;
        }
    }
        private void initViews() {
            constraintLayout = findViewById(R.id.constraintLayout);
            textInputLayoutPhone = findViewById(R.id.textInputLayoutPhone);
            textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
            textInputEditTextPhoneTV = findViewById(R.id.textInputEditTextPhone);
            textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);
            appCompatButtonLogin = findViewById(R.id.appCompatButtonLogin);
            textViewLinkRegister = findViewById(R.id.textViewLinkRegister);
        }
        private void initListeners() {
            appCompatButtonLogin.setOnClickListener(this);
            textViewLinkRegister.setOnClickListener(this);
        }
        private void initObjects() {
            databaseHelper = new DatabaseHelper(activity);
            inputValidation = new InputValidation(activity);
        }

        private void verifyFromSQLite() {
            if (!inputValidation.isInputEditTextFilled(textInputEditTextPhoneTV, textInputLayoutPhone, getString(R.string.error_message))) {
                return;
            }
            if (!inputValidation.isInputEditTextEmail(textInputEditTextPhoneTV, textInputLayoutPhone, getString(R.string.error_message))) {
                return;
            }
            if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message))) {
                return;
            }
            if (databaseHelper.checkUser(textInputEditTextPhoneTV.getText().toString().trim()
                    , textInputEditTextPassword.getText().toString().trim())) {
                Intent accountsIntent = new Intent(activity, Dashboard_Activity.class);
                accountsIntent.putExtra("EMAIL", textInputEditTextPhoneTV.getText().toString().trim());
                emptyInputEditText();
                startActivity(accountsIntent);
            } else {
                // Snack Bar to show success message that record is wrong
                Snackbar.make(constraintLayout, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
            }
        }
        private void emptyInputEditText() {
            textInputEditTextPhoneTV.setText(null);
            textInputEditTextPassword.setText(null);
        }
}
