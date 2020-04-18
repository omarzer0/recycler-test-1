package com.example.android.recylertest1.threads;

import android.os.AsyncTask;

import com.example.android.recylertest1.Contact;
import com.example.android.recylertest1.db.AppDatabase;

import java.util.List;

public class ContactThread extends AsyncTask<Void, Void, List<Contact>> {

    private AppDatabase appDatabase;
    private ContactCallback contactCallback;

    public ContactThread(AppDatabase appDatabase, ContactCallback contactCallback) {
        this.appDatabase = appDatabase;
        this.contactCallback = contactCallback;
    }

    @Override
    protected List<Contact> doInBackground(Void... voids) {
        return appDatabase.contactDao().getContactsList();
    }

    @Override
    protected void onPostExecute(List<Contact> contacts) {
        super.onPostExecute(contacts);
        contactCallback.getContactsList(contacts);
    }

    public interface ContactCallback {
        void getContactsList(List<Contact> contactsList);
    }
}
