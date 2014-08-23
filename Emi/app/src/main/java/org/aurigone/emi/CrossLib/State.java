package org.aurigone.emi.CrossLib;

import org.aurigone.emi.JSONReader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Created by anthony on 23.08.14.
 */
public class State extends JSONReader implements Serializable {
    protected String schedule;
    protected int task;

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public State(String name, int task) {
        this.schedule = name;
        this.task = task;
    }

    public State(InputStream stream) {
        load(stream);
    }

    public void load(InputStream stream){
        try {
            JSONObject o = getJsonObject(stream);
            task = o.getInt("current_task");
            schedule = o.getString("schedule");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void save(OutputStream stream){
        JSONObject o = new JSONObject();
        try {
            o.put("schedule", schedule);
            o.put("current_task", task);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        try {
            stream.write(o.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
