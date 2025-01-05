// Admin_dashboard.java
package com.example.holidayapplicationadmin.holidayapplicationadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.holidayapplicationadmin.R;

public class Admin_dashboard extends AppCompatActivity {

    Button btnAddEmployee, btnEditEmployee, btnDeleteEmployee, btnApproveHolidays, btnLogoutAdmin;
    DatabaseHelper databaseHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        databaseHelper = new DatabaseHelper(this);
        btnLogoutAdmin = findViewById(R.id.btnLogoutAdmin);
        String user = getIntent().getStringExtra("USERNAME");


        // Buttons
        btnAddEmployee = findViewById(R.id.btnAddEmployee);
        btnEditEmployee = findViewById(R.id.btnEditEmployee);
        btnDeleteEmployee = findViewById(R.id.btnDeleteEmployee);
        btnApproveHolidays = findViewById(R.id.btnApproveHolidays);
        btnLogoutAdmin = findViewById(R.id.btnLogoutAdmin);

        btnAddEmployee.setOnClickListener(v -> startActivity(new Intent(Admin_dashboard.this, add_employee.class)));
        //btnEditEmployee.setOnClickListener(v -> startActivity(new Intent(Admin_dashboard.this, EditEmployeeActivity.class)));
       // btnDeleteEmployee.setOnClickListener(v -> startActivity(new Intent(Admin_dashboard.this, DeleteEmployeeActivity.class)));
       // btnApproveHolidays.setOnClickListener(v -> startActivity(new Intent(Admin_dashboard.this, ApproveHolidaysActivity.class));

        // Set listeners for each button
        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddEmployee.setOnClickListener(v -> startActivity(new Intent(Admin_dashboard.this, add_employee.class)));
            }
        });

        btnEditEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Edit Employee activity
            }
        });

        btnDeleteEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDeleteEmployee.setOnClickListener(v -> startActivity(new Intent(Admin_dashboard.this, delete_employee.class)));
            }
        });

        btnApproveHolidays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Approve Holidays activity
            }
        });

        btnLogoutAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout() {
        Intent intent = new Intent(Admin_dashboard.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
