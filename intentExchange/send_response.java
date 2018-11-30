package home.com.intentexchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class send_response extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_response);
        String TAG="debug";
        TextView tv=findViewById(R.id.textView);
        String mystring=getIntent().getStringExtra("myvalue");
        Log.d(TAG, "onCreate: "+mystring);
        tv.setText(mystring);
        setResult(101);

    }
}
