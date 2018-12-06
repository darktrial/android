package home.com.recyleruiexample;

import android.graphics.drawable.Icon;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Function> functions;

    //String[] functions=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFunctions();
        RecyclerView recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        //adapter


        //FunctionAdapter adapter=new FunctionAdapter(this);
        IconAdapter adapter=new IconAdapter();
        recyclerView.setAdapter(adapter);

    }
    private void itemClicked(Function function){
        String TAG="clicked";
        Log.d(TAG, "itemClicked: "+function.getName());

    }
    private void setupFunctions() {
        functions = new ArrayList<>();
        String[] funcs=getResources().getStringArray(R.array.functions);
        functions.add(new Function(funcs[0],R.drawable.func_finance));
        functions.add(new Function(funcs[1],R.drawable.func_transaction));
        functions.add(new Function(funcs[2],R.drawable.func_balance));
        functions.add(new Function(funcs[3],R.drawable.func_contacts));
        functions.add(new Function(funcs[4],R.drawable.func_exit));
    }

    public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconHolder>{
        @NonNull
        @Override
        public IconHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view=getLayoutInflater().inflate(R.layout.item_icon,viewGroup,false);
            return new IconHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull IconHolder iconHolder, int i) {
            final Function function=functions.get(i);
            iconHolder.nameText.setText(function.getName());
            iconHolder.iconImage.setImageResource(function.icon);
            iconHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClicked(function);
                }
            });
        }

        @Override
        public int getItemCount() {
            //return 0;
            return functions.size();
        }

        public class IconHolder extends RecyclerView.ViewHolder {

            ImageView iconImage;
            TextView nameText;
            public IconHolder(@NonNull View itemView) {
                super(itemView);
                iconImage=itemView.findViewById(R.id.item_icon);
                nameText=itemView.findViewById(R.id.item_name);
            }
        }
    }
}
