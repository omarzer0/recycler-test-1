package com.example.android.recylertest1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int ADD_REQUEST_CODE = 1;
    final int EDIT_REQUEST_CODE = 2;
    Intent intent;

    FloatingActionButton btnFloatingAction;
    RecyclerView recyclerView;

    ContactAdapter.OnContactClickListener onContactClickListener = new ContactAdapter.OnContactClickListener() {
        @Override
        public void onClick(Contact contact, int position) {
            intent = new Intent(MainActivity.this, AddItemActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("contact", contact);
            startActivityForResult(intent, EDIT_REQUEST_CODE);
        }

        @Override
        public void delete(Contact contact) {
            contactList.remove(contact);
            contactAdapter.notifyDataSetChanged();
        }
    };
    ContactAdapter contactAdapter = new ContactAdapter(onContactClickListener);
    ArrayList<Contact> contactList = new ArrayList<>();

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(MainActivity.this, AddItemActivity.class);
            startActivityForResult(intent, ADD_REQUEST_CODE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find all ids in 1 method
        myFindById();

        recyclerView.setAdapter(contactAdapter);
        contactAdapter.contactArrayList = contactList;

        btnFloatingAction.setOnClickListener(onClickListener);


    }

    private void myFindById() {
        btnFloatingAction = findViewById(R.id.btnFabAdd);
        recyclerView = findViewById(R.id.recycler);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            contactList.add((Contact) data.getSerializableExtra("contact"));
            contactAdapter.notifyDataSetChanged();
        } else if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            contactList.set(data.getIntExtra("position", 0), (Contact) data.getSerializableExtra("contact"));
            contactAdapter.notifyDataSetChanged();
        }
    }
}
