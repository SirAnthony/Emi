package org.aurigone.emi.CrossLib;

import java.util.ArrayList;

/**
 * Created by anthony on 24.08.14.
 */
public class TaskGroup {
    protected Task[] tasks;

    public TaskGroup(ArrayList<Task> t){
        this.tasks = t.toArray(new Task[t.size()]);
    }

    public Task getTask(int index){
        return tasks[index];
    }

}
