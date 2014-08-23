package org.aurigone.emi.CrossLib;

import android.content.res.AssetManager;

import org.aurigone.emi.JSONReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by anthony on 23.08.14.
 */
public class Schedule extends JSONReader {
    protected static AssetManager assets;
    protected ArrayList<Task> tasks;
    protected String name;

    public Schedule(String filename){
        JSONObject schedule;
        JSONObject timings;
        JSONArray array;
        name = filename;
        try {
            schedule = getJsonObject(assets.open(filename));
            timings = schedule.getJSONObject("timings");
            array = schedule.getJSONArray("schedule");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        JSONArray sh;
        for (int n = 0; n < array.length(); ++n) {
            String name;
            try {
                name = array.getString(n);
                sh = timings.getJSONArray(name);
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
            tasks.add(new Task(name, t));
        }
    }

    public String getName(){
        return name;
    }

    public int length(){
        return tasks.size();
    }

    public Task getTask(int index){
        return tasks.get(index);
    }
}
