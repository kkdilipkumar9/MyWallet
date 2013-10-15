package com.example.mywallet;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends Activity{
	private TextView welcomeUserView=null;
	  ArrayList<String> homeNameList;
	  private ListView homeList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.login);
		setContentView(R.layout.homescreen);
	//	welcomeUserView= (TextView)	findViewById(R.id.homescreenuser);
		 homeList=(ListView)findViewById(R.id.homeListView);
		createHomeNameListView();
		 
	}
private void createHomeNameListView(){
	
	homeNameList=	new ArrayList<String>();
	getHomeNames();
    ArrayAdapter<String> arrayAdapter =     
            new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, homeNameList);
            // Set The Adapter
    homeList.setAdapter(arrayAdapter); 
}

private void getHomeNames()
{
	homeNameList.add("Banking");
	homeNameList.add("House");
	homeNameList.add("Insurance");
	homeNameList.add("Loan");
	homeNameList.add("Creditcard");
	homeNameList.add("DebitCard");
	homeNameList.add("Internet");
	homeNameList.add("Others");
//	homeNameList.add("RABBIT");
//	homeNameList.add("BEER");
//	homeNameList.add("DONKEY");
//	homeNameList.add("LAMB");
//	homeNameList.add("GOAT");
   
   
}
}
