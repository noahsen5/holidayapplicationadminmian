package com.example.holidayapplicationadmin.holidayapplicationadmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Login.db";
    private static final String TABLE_NAME = "Users";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "PASSWORD";

    //private static final String EMPLOYEE_TABLE = "Employees";
    private static final String COL_4 = "EMPLOYEE_NAME";
    private static final String COL_5 = "EMPLOYEE_ID";
    private static final String COL_6 = "HOLIDAY_APPROVED"; // Boolean (1 = Yes, 0 = No)

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Users table
        String createUserTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " TEXT UNIQUE, " + // Ensure unique usernames
                COL_3 + " TEXT)";
        db.execSQL(createUserTable);

        // Employees table
        String createEmployeeTable = "CREATE TABLE Employees (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "EMPLOYEE_NAME TEXT, " +
                "EMPLOYEE_ID TEXT UNIQUE, " + // Ensure unique employee IDs
                "HOLIDAY_APPROVED INTEGER DEFAULT 0)"; // 0 = Not Approved, 1 = Approved
        db.execSQL(createEmployeeTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE);
        onCreate(db);
    }

    /**
     * Inserts a new user into the database.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return true if insertion was successful, false otherwise.
     */
    public boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, username);
            contentValues.put(COL_3, password);

            long result = db.insert(TABLE_NAME, null, contentValues);
            return result != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    /**
     * Validates the user's login credentials.
     * @param username The username to check.
     * @param password The password to check.
     * @return The username if valid, null otherwise.
     */
    public String checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String result = null;

        try {
            String query = "SELECT " + COL_2 + " FROM " + TABLE_NAME +
                    " WHERE " + COL_2 + "=? AND " + COL_3 + "=?";
            Cursor cursor = db.rawQuery(query, new String[]{username, password});

            if (cursor != null && cursor.moveToFirst()) {
                result = cursor.getString(0); // Get the username
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close(); // Always close the database
        }

        return result; // Return null if no match is found
    }

    /**
     * Fetches all users from the database (for debugging purposes).
     * @return A Cursor pointing to the result set.
     */
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

    }


    /**
     * Adds a new employee to the Employees table.
     * @param name The employee's name.
     * @param id The employee's unique ID.
     * @return true if the employee was added successfully, false otherwise.
     */
    public boolean addEmployee(String name, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("EMPLOYEE_NAME", name);
            contentValues.put("EMPLOYEE_ID", id);
            contentValues.put("HOLIDAY_APPROVED", 0); // Default to not approved

            // Insert into Employees table
            long result = db.insert("Employees", null, contentValues);
            return result != -1; // Return true if insertion was successful
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }
    /**
     * Deletes an employee from the Employees table if both name and ID match.
     * @param name The employee's name.
     * @param id The employee's unique ID.
     * @return true if the employee was deleted successfully, false otherwise.
     */
    public boolean deleteEmployee(String name, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            // WHERE clause to match both name and ID
            int rowsAffected = db.delete("Employees", "EMPLOYEE_NAME=? AND EMPLOYEE_ID=?", new String[]{name, id});
            return rowsAffected > 0; // Return true if at least one row was deleted
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }


    /**
     * Updates holiday approval status for an employee.
     * @param employeeId The unique ID of the employee.
     * @param isApproved True if the holiday is approved, false otherwise.
     * @return true if the update was successful, false otherwise.
     */

    public boolean updateHolidayApproval(String employeeId, boolean isApproved) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("HOLIDAY_APPROVED", isApproved ? 1 : 0);

            int rowsAffected = db.update("Employees", contentValues, "EMPLOYEE_ID=?", new String[]{employeeId});
            return rowsAffected > 0; // Return true if at least one row was updated
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }



    /**
     * Fetches all employees from the Employees table.
     * @return A Cursor pointing to the result set.
     */
    public Cursor getAllEmployees() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    /**
     * Fetches an employee's holiday approval status.
     * @param employeeId The unique ID of the employee.
     * @return The approval status (1 for approved, 0 for not approved), or -1 if not found.
     */
    public int getHolidayApprovalStatus(String employeeId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_6 + " FROM " + TABLE_NAME + " WHERE " + COL_5 + "=?", new String[]{employeeId});

        if (cursor != null && cursor.moveToFirst()) {
            int status = cursor.getInt(0);
            cursor.close();
            return status;
        }
        return -1; // Not found
    }
}
