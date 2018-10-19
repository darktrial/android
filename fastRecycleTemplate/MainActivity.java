package home.com.fastrecycletemplate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RecyclerView rview=findViewById(R.id.recycler);
        rview.setHasFixedSize(true);
        //rview.setLayoutManager(new GridLayoutManager(this,4));
        rview.setLayoutManager(new LinearLayoutManager(this));
        Log.d("sampleAdapter", "onCreate: "+"start");
        sampleAdapter sa=new sampleAdapter();
        rview.setAdapter(sa);


    }
    public class sampleAdapter extends RecyclerView.Adapter<sampleAdapter.contentHolder>
    {
        private static final String TAG ="sampleAdapter" ;
        final String[] dataSet={"aa","bb","cc","dd"};
        @NonNull
        @Override
        public contentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            //return null;
            View view=getLayoutInflater().inflate(R.layout.data_list,viewGroup,false);
            return new contentHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull contentHolder contentHolder, int i) {
            contentHolder.tv.setText(dataSet[i]);
        }

        @Override
        public int getItemCount() {
            //return 0;
            Log.d(TAG, "getItemCount: "+ dataSet.length);
            return dataSet.length;
        }

        public class contentHolder extends RecyclerView.ViewHolder{
          TextView tv;
          public contentHolder(@NonNull View itemView) {
              super(itemView);
              tv=itemView.findViewById(R.id.dataName);
          }
      }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
