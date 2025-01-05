// MainActivity.java
package com.example.holidayapplicationadmin.holidayapplicationadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.holidayapplicationadmin.R;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    TextView signupText;
    Button loginButton;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.userNameText);
        password = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        signupText = findViewById(R.id.textSignup);
        databaseHelper = new DatabaseHelper(this);

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                } else {
                    String loggedInUserName = databaseHelper.checkLogin(user, pass);
                    if (loggedInUserName != null) {
                        if (pass.contains("1")) {
                            // Navigate to Employee Dashboard
                            Intent intent = new Intent(MainActivity.this, Employee_dashboard.class);
                            intent.putExtra("USERNAME", loggedInUserName);
                            startActivity(intent);
                        } else if (pass.contains("2")) {
                            // Navigate to Admin Dashboard
                            Intent intent = new Intent(MainActivity.this, Admin_dashboard.class);
                            intent.putExtra("USERNAME", loggedInUserName);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid access level", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
