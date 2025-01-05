// Employee_dashboard.java
package com.example.holidayapplicationadmin.holidayapplicationadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.holidayapplicationadmin.R;

public class Employee_dashboard extends AppCompatActivity {

    Button btnViewDetails, btnEditProfile, btnRequestHoliday, btnNotificationPreferences, btnLogoutEmployee;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);
        databaseHelper = new DatabaseHelper(this);
        btnLogoutEmployee = findViewById(R.id.btnLogoutEmployee);
        String user = getIntent().getStringExtra("USERNAME");

        // Buttons
        btnViewDetails = findViewById(R.id.btnViewDetails);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnRequestHoliday = findViewById(R.id.btnRequestHoliday);
        btnNotificationPreferences = findViewById(R.id.btnNotificationPreferences);
        btnLogoutEmployee = findViewById(R.id.btnLogoutEmployee);

        // Set listeners for each button
        btnViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to View Details activity
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Edit Profile activity
            }
        });

        btnRequestHoliday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Request Holiday activity
            }
        });

        btnNotificationPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Notification Preferences activity
            }
        });

        btnLogoutEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout() {
        Intent intent = new Intent(Employee_dashboard.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
