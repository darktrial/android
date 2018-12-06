package home.com.broadcastreceiverdemo;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


public class helloService extends IntentService{
    private static final String TAG ="hello service" ;
    public static final String ACTION_HELLO_DONE="action_hello_done";
    public helloService() {
        super("HelloService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: "+intent.getStringExtra("name"));
        Intent done=new Intent();
        done.setAction(ACTION_HELLO_DONE);
        sendBroadcast(done);
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.d(TAG, "onStartCommand: ");
//        //return super.onStartCommand(intent, flags, startId);
//        return START_NOT_STICKY;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
