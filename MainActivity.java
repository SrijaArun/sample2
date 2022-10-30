package com.example.sample2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, usn, branch, semester, Phone_Number, Parent_Phone_Number, Alternate_Phone_Number, DOB;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        usn = findViewById(R.id.usn);
        branch = findViewById(R.id.branch);
        semester = findViewById(R.id.semester);
        Phone_Number = findViewById(R.id.Phone_Number);
        Parent_Phone_Number = findViewById(R.id.Parent_Phone_Number);
        Alternate_Phone_Number = findViewById(R.id.Alternate_Phone_Number);
        DOB = findViewById(R.id.DOB);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String usnTXT = usn.getText().toString();
                String branchTXT = branch.getText().toString();
                String semesterTXT = semester.getText().toString();
                String Phone_NumberTXT = Phone_Number.getText().toString();
                String Parent_Phone_NumberTXT = Parent_Phone_Number.getText().toString();
                String Alternate_Phone_NumberTXT = Alternate_Phone_Number.getText().toString();
                String DOBTXT = DOB.getText().toString();

                boolean checkinsertdata = DB.insertuserdata(nameTXT, usnTXT, branchTXT, semesterTXT, Phone_NumberTXT, Parent_Phone_NumberTXT, Alternate_Phone_NumberTXT, DOBTXT);
                if (checkinsertdata == true) {
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }


            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String usnTXT = usn.getText().toString();
                String branchTXT = branch.getText().toString();
                String semesterTXT = semester.getText().toString();
                String Phone_NumberTXT = Phone_Number.getText().toString();
                String Parent_Phone_NumberTXT = Parent_Phone_Number.getText().toString();
                String Alternate_Phone_NumberTXT = Alternate_Phone_Number.getText().toString();
                String DOBTXT = DOB.getText().toString();

                boolean checkupdatedata = DB.updateuserdata(nameTXT, usnTXT, branchTXT, semesterTXT, Phone_NumberTXT, Parent_Phone_NumberTXT, Alternate_Phone_NumberTXT, DOBTXT);
                if (checkupdatedata == true) {
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
                }


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String usnTXT = usn.getText().toString();
                boolean checkdeletedata = DB.deletedata(nameTXT, usnTXT);
                if (checkdeletedata == true) {
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }


            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Cursor res= DB.getdata();
                if(res.getCount()==0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists!", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("USN :"+res.getString(0)+"\n");
                    buffer.append("Branch :"+res.getString(0)+"\n");
                    buffer.append("Semester :"+res.getString(0)+"\n");
                    buffer.append("Phone Number :"+res.getString(0)+"\n");
                    buffer.append("Parent's Phone Number :"+res.getString(0)+"\n");
                    buffer.append("Alternate Phone Number :"+res.getString(0)+"\n");
                    buffer.append("DOB :"+res.getString(0)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}