package com.example.mywallet;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {
	static int  id=1;
	User userData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);
 
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        Button registerButton = (Button) findViewById(R.id.btnRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ValidateData();
			}
		});
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
                                // Closing registration screen
                // Switching to Login Screen/closing register screen
                finish();
            }
        });
    }
    
    private void ValidateData(){
    	EditText fullName=	(EditText)findViewById(R.id.reg_fullname);
    	EditText email=	(EditText)findViewById(R.id.reg_email);
    	EditText password=	(EditText)findViewById(R.id.reg_password);
    	if(fullName.getText()==null||email.getText()==null||password.getText()==null ){
    		return;

    	}	
    	else{
    		userData = new User();
    		userData.setEmail(email.getText().toString());
    		//	userData.setId(""+ ++id);
    		userData.setPassword(password.getText().toString());
    		userData.setUserName(fullName.getText().toString());
    		registerUser(userData );
    	}
    }
    
    private void registerUser(User data){
    	DatabaseHandler db = new DatabaseHandler(this);
    	db.addContact(data);    
    	Log.d("Reading: ", "Reading all contacts.."); 
    	List<User> contacts = db.getAllContacts();      

    	for (User cn : contacts) {
    		String log = "Id: "+cn.getId()+" ,Name: " + cn.getUserName() + " ,Password: " + cn.getPassword();
    		// Writing Contacts to log
    		Log.d("Name: ", log);
    	}

    }
}