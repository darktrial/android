package home.com.switchexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TfiAG ="switchExample" ;
    private static final String TAG ="switchExample" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Switch sw=findViewById(R.id.)
        final TextView tv=findViewById(R.id.textView);
        Switch sw=findViewById(R.id.myswitch);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(TAG, "onCheckedChanged: "+b);
                if (b==false)
                {
                    tv.setText("false");
                }
                else tv.setText("true");
            }
        });
        /*sw.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Log.d(TfiAG, "onClick: ");
            }
        });*/
    }
}
