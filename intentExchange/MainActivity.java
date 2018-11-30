package home.com.intentexchange;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent=new Intent(this,send_response.class );
        final EditText et=findViewById(R.id.editText);
        String result=et.getText().toString();
        intent.putExtra("myvalue",result);
        Button bt=findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String result=et.getText().toString();
                intent.putExtra("myvalue",result);
                //startActivity(intent);
                startActivityForResult(intent,100);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            if (resultCode==101) Log.d("getresult", "onActivityResult: OK");
            else Log.d("getresult", "onActivityResult:NotOK ");
        }
    }
}
