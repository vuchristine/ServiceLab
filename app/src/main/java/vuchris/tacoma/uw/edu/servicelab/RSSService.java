package vuchris.tacoma.uw.edu.servicelab;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Christine on 5/19/2016.
 */
public class RSSService extends IntentService {

    //static variables
    private static final String TAG = "RSSService";
    private static final int POLL_INTERVAL = 5000; //5 seconds
    private static final String STACKOVERFLOW_URL =
            "http://stackoverflow.com/feeds/tag?tagnames=android&sort=newest";
    public static final String FEED = "feed";

    //variables
    private String mResult;


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public RSSService() {
        super("RSSService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //if the intent is not null show this message
        if (intent != null) {
            Log.d(TAG, "Performing the service");
        }
    }

    /**
     * Static method for creating alarm manager.
     * @param context
     * @param isOn
     */
    public static void setServiceAlarm(Context context, boolean isOn) {
        Intent i = new Intent(context, RSSService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, i, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(
                Context.ALARM_SERVICE);

        //if the alarm is on
        if (isOn) {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP
                    , 10
                    , POLL_INTERVAL, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

}
