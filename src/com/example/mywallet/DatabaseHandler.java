package com.example.mywallet;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	 private static final int DATABASE_VERSION = 1;
	 private static final String DATABASE_NAME = "MyWallet";
	 private static final String TABLE_CONTACTS = "userContacts";
	 
	 private static final String KEY_ID = "id";
	    private static final String KEY_NAME = "name";
	    private static final String KEY_EMAIL="email";
	    private static final String KEY_PASSWORD = "PASSWORD";
	    
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				  + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"  + KEY_NAME + " TEXT UNIQUE,"+KEY_PASSWORD +" Varchar,"
                + KEY_EMAIL + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	public void addContact(User userContact) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(KEY_NAME, userContact.getUserName()); // Contact Name
	//    values.put(KEY_ID, userContact.getId()); // 
	    values.put(KEY_PASSWORD, userContact.getPassword());
	    values.put(KEY_EMAIL, userContact.getEmail());
	    // Inserting Row
	    db.insert(TABLE_CONTACTS, null, values);
	    db.close(); // Closing database connection
	}
	
	   public List<User> getAllContacts() {
	        List<User> contactList = new ArrayList<User>();
	        // Select All Query
	        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
	 
	        SQLiteDatabase db = this.getWritableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	 
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	            	User contact = new User();
	                contact.setId(cursor.getString(0));
	                contact.setUserName(cursor.getString(1));
	                contact.setPassword(cursor.getString(2));
	                // Adding contact to list
	                contactList.add(contact);
	            } while (cursor.moveToNext());
	        }
	 
	        // return contact list
	        return contactList;
	    }
	   
	   public boolean userLogin(String userName, String password){
		   boolean isValidUser= false;
		   String loginQuery=null;
		   Cursor cursor = null;
		   try{
			   loginQuery="SELECT COUNT(" +KEY_NAME+") FROM "+TABLE_CONTACTS+" WHERE "+KEY_NAME+ " = '"+userName+"' AND "+ KEY_PASSWORD+ " = '"+password+"'";
			   SQLiteDatabase db = this.getReadableDatabase();
			   cursor = db.rawQuery(loginQuery, null);
		   }
		   catch(Exception e){
			   Log.d("there is an error:", loginQuery);
		   }

		   if(cursor. moveToFirst()){			   
			   int Count = Integer.parseInt(cursor.getString(0));
			   if(Count ==1){
				   isValidUser= true;
				   Log.d("****its valid***",""+Count );
			   }


		   }
		   return  isValidUser;
	   }
		   
		   
	   
}
