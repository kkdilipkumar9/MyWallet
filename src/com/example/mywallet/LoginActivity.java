package com.example.mywallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	String userName=null;
	String password= null;
	boolean loginSuccess= false;
	private SessionManager session;
	  AlertDialogManager alert = new AlertDialogManager();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        session = new SessionManager(getApplicationContext()); 
        Toast.makeText(getApplicationContext(), "user Login Status"+ session.isLoggedIn(), Toast.LENGTH_LONG).show();
        setContentView(R.layout.login);
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
 
   
        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
        
        Button loginButton = (Button) findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				// TODO Auto-generated method stub
				userLogin();
				openHomescreen();
			}
		});
        
    }

    protected void userLogin() {	
    	loginSuccess= false;
    	EditText userNameComponent=	(EditText)findViewById(R.id.user_Name);
    	EditText passwordComponent =(EditText)findViewById(R.id.password);
    	userName= userNameComponent.getText().toString();
    	password =passwordComponent.getText().toString();
    	if(userName.trim()!="" && password.trim() !=""){
    		DatabaseHandler db = new DatabaseHandler(this);

    		loginSuccess=	 db.userLogin(userName, password);

    	}
    }
    
    private void openHomescreen(){
    	if(loginSuccess){ 		
    		 session.createLoginSession("Android Hive", "anroidhive@gmail.com");
             
    		 Intent in = new Intent(getApplicationContext(), HomeActivity.class);
             startActivity(in); 		
             finish();
    	}
    	else{
    		 alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is incorrect", false);
    		
    	}

    }
}
