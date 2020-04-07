package com.example.android.recylertest1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int LAUNCH_SECOND_ACTIVITY = 1;

    FloatingActionButton btnFloatingAction;
    RecyclerView recyclerView;

    ContactAdapter contactAdapter = new ContactAdapter();
    ArrayList<Contact> contactList = new ArrayList<>();

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, add_item_layout.class);
            startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY);
//            startActivity(new Intent(MainActivity.this, add_item_layout.class));
//            contactList.add(new Contact("omar" , "" + random.nextInt()));
//            contactAdapter.notifyDataSetChanged();
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

        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                 contactList.add((Contact) data.getSerializableExtra("contact"));
                 contactAdapter.notifyDataSetChanged();
            }
        }
    }

    public void delete(View view) {
        //delete item here
    }
}
