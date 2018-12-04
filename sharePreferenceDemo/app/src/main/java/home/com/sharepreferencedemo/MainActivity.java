package home.com.sharepreferencedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String mytext = null;
        String myinput=getSharedPreferences("testapp",MODE_PRIVATE).getString("myinput","");
        EditText et=findViewById(R.id.editText);
        et.setText(myinput);
        Button bt=findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et=findViewById(R.id.editText);
                getSharedPreferences("testapp",MODE_PRIVATE).edit().putString("myinput",et.getText().toString()).commit();
            }
        });
        //getSharedPreferences("testapp",MODE_PRIVATE).edit().putString("myinput")
    }
}
