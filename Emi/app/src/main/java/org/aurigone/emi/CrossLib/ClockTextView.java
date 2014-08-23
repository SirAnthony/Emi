package org.aurigone.emi.CrossLib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by anthony on 24.08.14.
 */
public class ClockTextView extends TextView {

    private int countdown = 0;

    private ActionSwitcher listener;
    private final Timer timer = new Timer();

    public ClockTextView(Context c) { super(c); }
    public ClockTextView(Context c, AttributeSet as) { super(c, as); }
    public ClockTextView(Context c, AttributeSet as, int d) { super(c, as, d); }

    public interface ActionSwitcher {
        public void switchAction();
    }

    private class DefaultTask extends TimerTask {
        @Override
        public void run(){
            updateText();
            countdown--;
        }
    }

    public void setListener(ActionSwitcher listener) {
        this.listener = listener;
    }

    public void setItem(Item item){
        this.countdown = item.getLength();
        timer.schedule(new DefaultTask(), 1);
    }

    void stop(){
        timer.cancel();
        timer.purge();
        listener.switchAction();
    }

    void updateText(){
        if (countdown < 0){
            stop();
        } else {
            setText(String.format("%02d:%02d", countdown / 60, countdown % 60));
        }
    }

}
