package home.com.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

class getSetHelper extends SQLiteOpenHelper
{


    public getSetHelper(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE nameValuePair (_id INTEGER PRIMARY KEY NOT NULL," +
                "name VARCHAR NOT NULL," +
                "value VARCHAR NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText edName=findViewById(R.id.editName);
        final EditText edValue=findViewById(R.id.editValue);
        Button btget=findViewById(R.id.getValue);
        Button btset=findViewById(R.id.setValue);
//        final String name=edName.getText().toString();
//        final String value=edValue.getText().toString();

        btset.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "set clicked";

            @Override
            public void onClick(View view) {
                getSetHelper gsh=new getSetHelper(MainActivity.this,"mydb",null,1);
                ContentValues values=new ContentValues();
                String name=edName.getText().toString();
                String value=edValue.getText().toString();
                values.put("name",name);
                values.put("value",value);

                long id=gsh.getWritableDatabase().insert("nameValuePair",null,values);
                if (id>-1){
                    Toast.makeText(MainActivity.this,"add success",Toast.LENGTH_SHORT).show();
                }
                else Log.d(TAG, "onClick: "+String.valueOf(id));
                gsh.close();



            }
        });

        btget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] columns ={"name","value"};
                String name=edName.getText().toString();
                Log.d("query value", "onClick: "+name);
                getSetHelper gsh=new getSetHelper(MainActivity.this,"mydb",null,1);
                Cursor cursor=gsh.getReadableDatabase().query("nameValuePair",null,"name=?",new String[]{name},null,null,null);
                cursor.moveToPosition(0);
                Log.d("get clicked", "onClick: "+String.valueOf(cursor.getCount()));
                Log.d("get clicked", "onClick: "+String.valueOf(cursor.getColumnIndex("value")));
                Log.d("get clicked", "onClick: "+cursor.getString(cursor.getColumnIndex("value")));
                if (cursor.getCount()>0)
                    edValue.setText(cursor.getString(cursor.getColumnIndex("value")));
                else Toast.makeText(MainActivity.this,"read error",Toast.LENGTH_SHORT).show();

                gsh.close();


            }
        });




    }

}
