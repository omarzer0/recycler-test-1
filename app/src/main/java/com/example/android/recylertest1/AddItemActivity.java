package com.example.android.recylertest1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddItemActivity extends AppCompatActivity {

    EditText edName, edNumber;
    FloatingActionButton floatingActionButton;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Contact contact = checkForError(edName.getText().toString(), edNumber.getText().toString());
            if (contact != null) {
                Intent resault_Intent = new Intent();
                resault_Intent.putExtra("contact", contact);
                int position_of_comming_item = getIntent().getIntExtra("position", 0);
                resault_Intent.putExtra("position", position_of_comming_item);
                setResult(Activity.RESULT_OK, resault_Intent);
                finish();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_layout);
        //find all ids in 1 method
        myFindById();
        if (getIntent().hasExtra("contact")) {
            Contact contact = (Contact) getIntent().getSerializableExtra("contact");
            edName.setText(contact.getName());
            edNumber.setText(contact.getNumber());
        }
        //onclick
        floatingActionButton.setOnClickListener(onClickListener);

    }

    private void myFindById() {
        edName = findViewById(R.id.edName);
        edNumber = findViewById(R.id.edNumber);
        floatingActionButton = findViewById(R.id.btnFabDone);
    }

    private Contact checkForError(String name, String number) { //if you add img add error detect for it
        boolean allIsFine = true;
        if (name == null || name.isEmpty()) {
            edName.setError("Enter A Name!");
            allIsFine = false;
        }
        if (number == null || number.isEmpty()) {
            edNumber.setError("Enter A Number!");
            allIsFine = false;
        }
        if (allIsFine) {
            return new Contact(name, number);
        } else {
            return null;
        }
    }
}
