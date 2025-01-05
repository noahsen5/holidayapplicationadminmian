package com.example.holidayapplicationadmin.holidayapplicationadmin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.holidayapplicationadmin.R;

public class add_employee extends AppCompatActivity {

    EditText etEmployeeName, etEmployeeId;
    Button btnSaveEmployee;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        // Initialize Views
        etEmployeeName = findViewById(R.id.etEmployeeName);
        etEmployeeId = findViewById(R.id.etEmployeeId);
        btnSaveEmployee = findViewById(R.id.btnSaveEmployee);

        // Initialize Database Helper
        databaseHelper = new DatabaseHelper(this);

        btnSaveEmployee.setOnClickListener(view -> {
            String name = etEmployeeName.getText().toString().trim();
            String id = etEmployeeId.getText().toString().trim();

            if (name.isEmpty() || id.isEmpty()) {
                Toast.makeText(add_employee.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean inserted = databaseHelper.addEmployee(name, id);
            if (inserted) {
                Toast.makeText(add_employee.this, "Employee Added Successfully", Toast.LENGTH_SHORT).show();
                etEmployeeName.setText(""); // Clear the fields after success
                etEmployeeId.setText("");
            } else {
                Toast.makeText(add_employee.this, "Failed to Add Employee. Employee ID may already exist.", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
