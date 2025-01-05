package com.example.holidayapplicationadmin.holidayapplicationadmin;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.holidayapplicationadmin.R;

public class delete_employee extends AppCompatActivity {

    EditText etDeleteEmployeeName, etDeleteEmployeeId;
    Button btnDeleteEmployee;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_employee);

        // Initialize Views
        etDeleteEmployeeName = findViewById(R.id.etDeleteEmployeeName);
        etDeleteEmployeeId = findViewById(R.id.etDeleteEmployeeId);
        btnDeleteEmployee = findViewById(R.id.btnDeleteEmployee);

        // Initialize Database Helper
        databaseHelper = new DatabaseHelper(this);

        // Button Click Listener
        btnDeleteEmployee.setOnClickListener(view -> {
            String name = etDeleteEmployeeName.getText().toString().trim();
            String id = etDeleteEmployeeId.getText().toString().trim();

            // Validate input fields
            if (name.isEmpty() || id.isEmpty()) {
                Toast.makeText(delete_employee.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // Delete Employee from Database
            boolean deleted = databaseHelper.deleteEmployee(name, id);
            if (deleted) {
                Toast.makeText(delete_employee.this, "Employee Deleted Successfully", Toast.LENGTH_SHORT).show();
                etDeleteEmployeeName.setText(""); // Clear the fields after success
                etDeleteEmployeeId.setText("");
            } else {
                Toast.makeText(delete_employee.this, "Failed to Delete Employee. Name or ID may not match.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
