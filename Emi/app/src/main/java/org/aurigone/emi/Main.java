package org.aurigone.emi;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.aurigone.emi.CrossLib.State;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Main extends ActionBarActivity {

    protected State state = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public State createConfig(){
        State s = new State("default_cross.json", 0, 0);
        try {
            FileOutputStream stream = openFileOutput("state.json", 0);
            s.save(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public void loadState(){
        try {
            FileInputStream stream = openFileInput("state.json");
            state = new State(stream);
            stream.close();
        } catch (FileNotFoundException e) {
            state = createConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startCross(View v){
        Intent intent = new Intent(this, Cross.class);
        if (state == null) {
            loadState();
        }
        intent.putExtra("STATE", state);
        startActivity(intent);
    }

}


