package org.aurigone.emi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.aurigone.emi.CrossLib.ClockTextView;
import org.aurigone.emi.CrossLib.Item;
import org.aurigone.emi.CrossLib.Schedule;
import org.aurigone.emi.CrossLib.State;
import org.aurigone.emi.CrossLib.Task;

import java.util.Iterator;


public class Cross extends Activity implements ClockTextView.ActionSwitcher {

    private ClockTextView chronometer;
    private Iterator<Item> taskIter;

    void load(State st) {
        Schedule sc = new Schedule(st.getSchedule());
        Task currentTask = sc.getTask(st.getTask());
        taskIter = currentTask.getItemsIter();
        switchAction();
    }

    @Override
    public void switchAction() {
        if (!taskIter.hasNext()) {
            return;
        }
        Item action = taskIter.next();
        chronometer.setItem(action);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        State state;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cross);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_cross, null);
        chronometer = (ClockTextView ) view.findViewById(R.id.TimeLeft);
        chronometer.setListener(this);
        Bundle extras = getIntent().getExtras();
        state = (State) extras.get("STATE");
        this.load(state);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cross, menu);
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
}
