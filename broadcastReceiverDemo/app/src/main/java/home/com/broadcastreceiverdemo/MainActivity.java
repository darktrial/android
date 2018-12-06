package home.com.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    private static final String TAG ="main activity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //service
        Intent myhelloService=new Intent(this,home.com.broadcastreceiverdemo.helloService.class);
        myhelloService.putExtra("name","t1");
        startService(myhelloService);
        myhelloService.putExtra("name","t2");
        startService(myhelloService);
        myhelloService.putExtra("name","t3");
        startService(myhelloService);
    }
    BroadcastReceiver br=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: hello "+intent.getAction());
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter=new IntentFilter((helloService.ACTION_HELLO_DONE));
        registerReceiver(br,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(br);
    }
}
