package home.com.contentprovider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CONTACT = 80;
    private static final String TAG ="READCONTACT" ;
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
        int permission=ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS);
        if (permission==PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "onCreate: "+"read contact");
            readContacts();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},REQUEST_CONTACT);
        }
        /*Intent myit=new Intent(this,mytest.class);
        startActivity(myit);;*/

    }

    private void readContacts() {
        Cursor cursor=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null,null,null);
        List<Contact> contacts=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Contact contact=new Contact(id,name);
            int hasPhone=cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER));

            Log.d(TAG, "readContacts: "+name);
            if (hasPhone==1){
                Cursor c2=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",new String[]{String.valueOf(id)},null);
                while (c2.moveToNext()){
                    String phone=c2.getString(c2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
                    Log.d(TAG, "phonenumber: "+ phone);
                    contact.getPhones().add(phone);
                }
            }
            contacts.add(contact);
        }
        ContactAdapter adapter=new ContactAdapter(contacts);
        RecyclerView recv=findViewById(R.id.recycler);
        recv.setHasFixedSize(true);
        recv.setLayoutManager(new LinearLayoutManager(this));
        recv.setAdapter(adapter);


    }

    public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder>{

        List<Contact> contacts;

        public ContactAdapter(List<Contact> contacts){
            this.contacts=contacts;
        }

        @NonNull
        @Override
        public ContactHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view=getLayoutInflater().inflate(android.R.layout.simple_list_item_2,viewGroup,false);
            return new ContactHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactHolder contactHolder, int i) {
            Contact contact=contacts.get(i);
            Log.d(TAG, "onBindViewHolder: "+contact.getName());
            contactHolder.nameText.setText(contact.getName());
            Log.d(TAG, "onBindViewHolder: "+"downg");
            StringBuilder sb=new StringBuilder();
            for ( String phone: contact.getPhones()){
                Log.d(TAG, "onBindViewHolder: "+phone);
                sb.append(phone);
                sb.append(" ");

                
            }
            //Log.d(TAG, "onBindViewHolder: "+sb);
            contactHolder.phoneText.setText(sb.toString());
        }

        @Override
        public int getItemCount() {
            Log.d(TAG, "getItemCount: "+contacts.size());
            return contacts.size();
        }

        public class ContactHolder extends RecyclerView.ViewHolder{

            TextView nameText;
            TextView phoneText;
            public ContactHolder(@NonNull View itemView) {
                super(itemView);
                nameText=itemView.findViewById(android.R.id.text1);
                phoneText=itemView.findViewById(android.R.id.text2);
                
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_CONTACT){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                readContacts();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_upload) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
