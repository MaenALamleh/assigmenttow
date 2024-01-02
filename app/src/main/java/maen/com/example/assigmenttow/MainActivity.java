package maen.com.example.assigmenttow;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

        private EditText editTextStudentId, editTextPassword;
        private Button Logbtn,btnSS;
    private CheckBox rememberMeCheckBox;


    private static final String PREFS_NAME = "MyPrefs";
        private static final String KEY_STUDENT_ID = "student_id";
        private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER_ME = "remember_me";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextStudentId = findViewById(R.id.editTextStudentId);
        editTextPassword = findViewById(R.id.editTextPassword);
        Logbtn = findViewById(R.id.Logbtn);
        btnSS = findViewById(R.id.btnSS);
        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean rememberMe = preferences.getBoolean(KEY_REMEMBER_ME, false);
        rememberMeCheckBox.setChecked(rememberMe);

        if (rememberMe) {

            String savedStudentId = preferences.getString(KEY_STUDENT_ID, "");
            String savedPassword = preferences.getString(KEY_PASSWORD, "");

            editTextStudentId.setText(savedStudentId);
            editTextPassword.setText(savedPassword);
        }

        Logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignUp();
            }
        });
    }

    private void login() {
        String studentId = editTextStudentId.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        boolean rememberMe = rememberMeCheckBox.isChecked();

        if (rememberMe) {
            SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(KEY_REMEMBER_ME, true);
            editor.putString(KEY_STUDENT_ID, studentId);
            editor.putString(KEY_PASSWORD, password);
            editor.apply();
        }

        if (isUserSignedUp(studentId, password)) {

            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(MainActivity.this, "Invalid credentials. Please sign up first.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isUserSignedUp(String enteredStudentId, String enteredPassword) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedStudentId = preferences.getString(KEY_STUDENT_ID, "");
        String savedPassword = preferences.getString(KEY_PASSWORD, "");
        return enteredStudentId.equals(savedStudentId) && enteredPassword.equals(savedPassword);
    }

    private void navigateToSignUp() {
        Intent intent = new Intent(MainActivity.this, MainActivity3_signUp.class);
        startActivity(intent);
    }
}
