package com.example.android.recylertest1.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.recylertest1.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contacts")
    List<Contact> getContactsList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void InsertContact(Contact contact);

    @Update
    public void updateContact(Contact contact);

    @Delete
    public void deleteContact(Contact contact);
}
