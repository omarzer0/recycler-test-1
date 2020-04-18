package com.example.android.recylertest1.threads;

import android.os.AsyncTask;

import com.example.android.recylertest1.Contact;
import com.example.android.recylertest1.db.AppDatabase;

import java.util.List;

public class UpdateContactThread extends AsyncTask<Contact, Void, List<Contact>> {

    private AppDatabase appDatabase;
    private ContactThread.ContactCallback contactCallback;

    public UpdateContactThread(AppDatabase appDatabase, ContactThread.ContactCallback contactCallback) {
        this.appDatabase = appDatabase;
        this.contactCallback = contactCallback;
    }

    @Override
    protected List<Contact> doInBackground(Contact... contacts) {
        for (Contact c : contacts) {
            appDatabase.contactDao().updateContact(c);
        }
        return appDatabase.contactDao().getContactsList();
    }

    @Override
    protected void onPostExecute(List<Contact> contactList) {
        super.onPostExecute(contactList);
        contactCallback.getContactsList(contactList);
    }
}
