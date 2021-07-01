package Activity;

import android.content.Intent;
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

public class SignIn_Activity extends AppCompatActivity implements View.OnClickListener{
    private final AppCompatActivity activity = SignIn_Activity.this;
    private ConstraintLayout signInConstraintLT;
    private TextInputLayout textInputLayoutNameLT;
    private TextInputLayout textInputLayoutPhoneLT;
    private TextInputLayout textInputLayoutPasswordLT;
    private TextInputLayout textInputLayoutConfirmPasswordLT;
    private TextInputEditText textInputEditTextNameTV;
    private TextInputEditText textInputEditTextPhoneTV;
    private TextInputEditText textInputEditTextPasswordTV;
    private TextInputEditText textInputEditTextConfirmPasswordTV;
    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initViews();
        initListeners();
        initObjects();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;
            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }
    private void initViews() {
        signInConstraintLT = findViewById(R.id.signInConstraintLayout);
        textInputLayoutNameLT = findViewById(R.id.textInputLayoutName);
        textInputEditTextPhoneTV = findViewById(R.id.textInputEditTextPhone);
        textInputLayoutPasswordLT = findViewById(R.id.textInputLayoutPasswordSignIn);
        textInputLayoutConfirmPasswordLT = findViewById(R.id.textInputLayoutConfirmPassword);
        textInputEditTextNameTV = findViewById(R.id.textInputEditTextName);
        textInputLayoutPhoneLT = findViewById(R.id.textInputLayoutPhone);
        textInputEditTextPasswordTV = findViewById(R.id.textInputEditTextPasswordSignIn);
        textInputEditTextConfirmPasswordTV = findViewById(R.id.textInputEditTextConfirmPassword);
        appCompatButtonRegister = findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLoginLink = findViewById(R.id.appCompatTextViewLoginLink);
    }
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextNameTV, textInputLayoutNameLT, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPhoneTV, textInputLayoutPhoneLT, getString(R.string.error_message))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextPhoneTV, textInputLayoutPhoneLT, getString(R.string.error_message))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPasswordTV, textInputLayoutPasswordLT, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPasswordTV, textInputEditTextConfirmPasswordTV,
                textInputLayoutConfirmPasswordLT, getString(R.string.error_password_match))) {
            return;
        }
        if (!databaseHelper.checkUser(textInputEditTextPhoneTV.getText().toString().trim())) {
            user.setName(textInputEditTextNameTV.getText().toString().trim());
            user.setPhone(textInputEditTextPhoneTV.getText().toString().trim());
            user.setPassword(textInputEditTextPasswordTV.getText().toString().trim());
            databaseHelper.addUser(user);
            // Snack Bar to show success message that record saved successfully
            Snackbar.make(signInConstraintLT, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
            Intent accountsIntent = new Intent(activity, MainActivity.class);
            startActivity(accountsIntent);
        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(signInConstraintLT, getString(R.string.error_phone_exists), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        textInputEditTextNameTV.setText(null);
        textInputEditTextPhoneTV.setText(null);
        textInputEditTextPasswordTV.setText(null);
        textInputEditTextConfirmPasswordTV.setText(null);
    }
}