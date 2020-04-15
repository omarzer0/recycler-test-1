package com.example.android.recylertest1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recylertest1.db.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final int ADD_REQUEST_CODE = 1;
    final int EDIT_REQUEST_CODE = 2;
    Intent intent;

    AppDatabase db;
    List<Contact> list;

    FloatingActionButton btnFloatingAction;
    RecyclerView recyclerView;

    ContactAdapter.OnContactClickListener onContactClickListener = new ContactAdapter.OnContactClickListener() {
        @Override
        public void onClick(Contact contact) {
            intent = new Intent(MainActivity.this, AddItemActivity.class);
            intent.putExtra("position", contactList.indexOf(contact));
            intent.putExtra("contact", contact);
            startActivityForResult(intent, EDIT_REQUEST_CODE);
        }

        @Override
        public void delete(Contact contact) {
//            contactList.remove(contact);
//            ArrayList<Contact> tempArrayList = new ArrayList<>(contactList);
//            contactAdapter.submitList(tempArrayList);
            db.contactDao().deleteContact(contact);
            list = db.contactDao().getContactsList();
            contactAdapter.submitList(list);
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

        //add database
        db = AppDatabase.getInstance(this);

        list = db.contactDao().getContactsList();
//        list.add(new Contact("omar" , "101010"));
//        list.add(new Contact("omar" , "101010"));
//        list.add(new Contact("omar" , "101010"));
//        contactList.addAll(list);
        contactAdapter.submitList(list);

        //find views by id
        btnFloatingAction = findViewById(R.id.btnFabAdd);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(contactAdapter);
        btnFloatingAction.setOnClickListener(onClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            contactList.add((Contact) data.getSerializableExtra("contact"));
//            ArrayList<Contact> tempArrayList = new ArrayList<>(contactList);
//            contactAdapter.submitList(tempArrayList);
            db.contactDao().InsertContact((Contact) data.getSerializableExtra("contact"));
            list = db.contactDao().getContactsList();
//            contactList.addAll(list);
            contactAdapter.submitList(list);
        }


        else if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            contactList.set(data.getIntExtra("position", 0), (Contact) data.getSerializableExtra("contact"));
//            ArrayList<Contact> tempArrayList = new ArrayList<>(contactList);
//            contactAdapter.submitList(tempArrayList);
            db.contactDao().updateContact((Contact) data.getSerializableExtra("contact"));
//            list.set(data.getIntExtra("position", 0) , (Contact) data.getSerializableExtra("contact"));
            list = db.contactDao().getContactsList();
            contactAdapter.submitList(list);
        }
    }
}
