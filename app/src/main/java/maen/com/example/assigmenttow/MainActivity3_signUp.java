package maen.com.example.assigmenttow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3_signUp extends AppCompatActivity {

    private EditText editTextStudentId, editTextPassword;
    private Button btnSignUp;

    // SharedPreferences key constants
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_PASSWORD = "password";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_sign_up);

        editTextStudentId = findViewById(R.id.editTextStudentId);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        String studentId = editTextStudentId.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Perform sign-up logic
        if (!studentId.isEmpty() && !password.isEmpty()) {
            // Save data in SharedPreferences
            saveUserData(studentId, password);

            // Display a toast message
            Toast.makeText(MainActivity3_signUp.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();

            // Optionally, navigate to another activity after successful sign-up
            Intent intent = new Intent(MainActivity3_signUp.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close the SignUpActivity
        } else {
            Toast.makeText(MainActivity3_signUp.this, "Please enter valid student ID and password", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserData(String studentId, String password) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_STUDENT_ID, studentId);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }
}
