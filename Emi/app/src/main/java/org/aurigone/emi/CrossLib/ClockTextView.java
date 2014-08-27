package org.aurigone.emi.CrossLib;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by anthony on 24.08.14.
 */
public class ClockTextView extends TextView {
    final long interval = 1000;
    private ActionSwitcher listener;
    private ChronoTimer timer;
    private Handler handler = new Handler();

    public ClockTextView(Context c) { super(c); }
    public ClockTextView(Context c, AttributeSet as) { super(c, as); }
    public ClockTextView(Context c, AttributeSet as, int d) { super(c, as, d); }

    public void stop() {
        timer.cancel();
        timer = null;
    }

    public interface ActionSwitcher {
        public void actionFinished();
    }

    private class ChronoTimer extends CountDownTimer {

        ChronoTimer(long t, long i) { super(t, i); }

        @Override
        public void onFinish() {
            updateText(0);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() { listener.actionFinished(); }
            }, 1500);
        }

        @Override
        public void onTick(long timeLeft) {
            updateText(timeLeft);
        }
    }

    public void setListener(ActionSwitcher listener) {
        this.listener = listener;
    }

    public void setItem(Item item){
        long total = (item.getLength() + 1) * interval;
        timer = new ChronoTimer(total, interval);
        updateText(total);
        timer.start();
    }

    void updateText(long timeLeft){
        long time = timeLeft / interval;
        final String sstime = String.format("%02d:%02d", time / 60, time % 60);
        setText(sstime);
    }


}
