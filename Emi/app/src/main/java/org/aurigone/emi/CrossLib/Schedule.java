package org.aurigone.emi.CrossLib;

import android.content.res.AssetManager;

import org.aurigone.emi.JSONReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by anthony on 23.08.14.
 */
public class Schedule extends JSONReader {
    protected ArrayList<TaskGroup> tasks = new ArrayList<TaskGroup>();
    protected String name;

    public Schedule(String name, InputStream stream){
        JSONObject schedule;
        JSONObject timings;
        JSONArray array;
        this.name = name;
        try {
            schedule = getJsonObject(stream);
            timings = schedule.getJSONObject("timings");
            array = schedule.getJSONArray("schedule");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        for (int g = 0; g < array.length(); ++g) {
            try {
                JSONArray arr = array.getJSONArray(g);
                TaskGroup group = new TaskGroup(createGroup(arr, timings));
                tasks.add(group);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    private ArrayList<Task> createGroup(JSONArray array, JSONObject timings){
        JSONArray sh;
        ArrayList<Task> group = new ArrayList<Task>();
        for (int n = 0; n < array.length(); ++n) {

            try {
                String timing = array.getString(n);
                sh = timings.getJSONArray(timing);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
            int[] t = new int[sh.length()];
            for (int j = 0; j < sh.length(); ++j){
                try {
                    t[j] = sh.getInt(j);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            group.add(new Task(t));
        }
        return group;
    }

    public String getName(){
        return name;
    }

    public int length(){
        return tasks.size();
    }

    public Task getTask(int subgrop, int index){
        return tasks.get(subgrop).getTask(index);
    }
}
