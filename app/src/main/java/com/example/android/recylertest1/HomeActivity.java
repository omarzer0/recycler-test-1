package com.example.android.recylertest1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recylertest1.db.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.android.recylertest1.utils.Constants.BOOLEAN_EXTRA;
import static com.example.android.recylertest1.utils.Constants.MY_PREFS_NAME;

public class HomeActivity extends AppCompatActivity {

    final int ADD_REQUEST_CODE = 1;
    final int EDIT_REQUEST_CODE = 2;
    private ImageView logoutBtn;
    Intent intent;

    AppDatabase db;
    List<Contact> list;

    FloatingActionButton btnFloatingAction;
    RecyclerView recyclerView;

    ContactAdapter.OnContactClickListener onContactClickListener = new ContactAdapter.OnContactClickListener() {
        @Override
        public void onClick(Contact contact) {
            intent = new Intent(HomeActivity.this, AddItemActivity.class);
            intent.putExtra("position", list.indexOf(contact));
            intent.putExtra("contact", contact);
            startActivityForResult(intent, EDIT_REQUEST_CODE);
        }

        @Override
        public void delete(Contact contact) {
            db.contactDao().deleteContact(contact);
            list = db.contactDao().getContactsList();
            contactAdapter.submitList(list);
        }

    };
    ContactAdapter contactAdapter = new ContactAdapter(onContactClickListener);
//    ArrayList<Contact> contactList = new ArrayList<>();

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(HomeActivity.this, AddItemActivity.class);
            startActivityForResult(intent, ADD_REQUEST_CODE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //add database
        db = AppDatabase.getInstance(this);

        list = db.contactDao().getContactsList();
        contactAdapter.submitList(list);

        //find views by id
        btnFloatingAction = findViewById(R.id.btnFabAdd);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(contactAdapter);
        btnFloatingAction.setOnClickListener(onClickListener);
        logoutBtn = findViewById(R.id.activity_home_img_exit_image_view);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
                editor.putBoolean(BOOLEAN_EXTRA,false);
                editor.apply();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            db.contactDao().InsertContact((Contact) data.getSerializableExtra("contact"));
            list = db.contactDao().getContactsList();
            contactAdapter.submitList(list);
        }


        else if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            db.contactDao().updateContact((Contact) data.getSerializableExtra("contact"));
            list = db.contactDao().getContactsList();
            contactAdapter.submitList(list);
        }
    }
}
