package org.aurigone.emi.CrossLib;

import org.aurigone.emi.JSONReader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Created by anthony on 23.08.14.
 */
public class State extends JSONReader implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String schedule;
    protected int group;
    protected int task;


    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    public String getFilename() {
        return schedule;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public State(String name, int group, int task) {
        this.schedule = name;
        this.group = group;
        this.task = task;
    }

    public State(InputStream stream) throws FileNotFoundException {
        try {
            load(stream);
        } catch (JSONException e) {
            throw new FileNotFoundException();
        }
    }

    public void load(InputStream stream) throws JSONException {
        try {
            JSONObject o = getJsonObject(stream);
            int uid = o.getInt("serialVersionUID");
            if (uid != this.serialVersionUID) {
                throw new JSONException("Wrong file version");
            }
            task = o.getInt("current_task");
            group = o.getInt("current_group");
            schedule = o.getString("schedule");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(OutputStream stream){
        JSONObject o = new JSONObject();
        try {
            o.put("serialVersionUID", serialVersionUID);
            o.put("schedule", schedule);
            o.put("current_group", group);
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
