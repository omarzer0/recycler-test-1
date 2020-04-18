package com.example.android.recylertest1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recylertest1.db.AppDatabase;
import com.example.android.recylertest1.threads.AddContactThread;
import com.example.android.recylertest1.threads.ContactThread;
import com.example.android.recylertest1.threads.DeleteContactThread;
import com.example.android.recylertest1.threads.UpdateContactThread;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    final int ADD_REQUEST_CODE = 1;
    final int EDIT_REQUEST_CODE = 2;
    Intent intent;
    AppDatabase db;
    FloatingActionButton btnFloatingAction;
    RecyclerView recyclerView;

    ContactThread.ContactCallback submitListCallback = new ContactThread.ContactCallback() {
        @Override
        public void getContactsList(List<Contact> contactsList) {
            contactAdapter.submitList(contactsList);
        }
    };


    ContactAdapter.OnContactClickListener onContactClickListener = new ContactAdapter.OnContactClickListener() {
        @Override
        public void onClick(final Contact contact) {
            // contact thread has the list of contacts
            ContactThread contactThread = new ContactThread(db, new ContactThread.ContactCallback() {
                @Override
                public void getContactsList(List<Contact> contactsList) {
                    intent = new Intent(MainActivity.this, AddItemActivity.class);
                    intent.putExtra("position", contactsList.indexOf(contact));
                    intent.putExtra("contact", contact);
                    startActivityForResult(intent, EDIT_REQUEST_CODE);
                }
            });
            contactThread.execute();
        }

        @Override
        public void delete(final Contact contact) {
            DeleteContactThread deleteContactThread = new DeleteContactThread(db, submitListCallback);
            deleteContactThread.execute(contact);
        }

    };
    ContactAdapter contactAdapter = new ContactAdapter(onContactClickListener);

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
        ContactThread contactThread = new ContactThread(db, submitListCallback);
        contactThread.execute();

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
            AddContactThread addContactThread = new AddContactThread(db, submitListCallback);
            addContactThread.execute((Contact) data.getSerializableExtra("contact"));
        } else if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            UpdateContactThread updateContactThread = new UpdateContactThread(db, new ContactThread.ContactCallback() {
                @Override
                public void getContactsList(List<Contact> contactsList) {
                    contactAdapter.submitList(contactsList);
                }
            });
            updateContactThread.execute((Contact) data.getSerializableExtra("contact"));
        }
    }
}
