package fr.univ_smb.cheapsprint.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.adapters.ShoppingDetailsAdapter;

public class ShoppingDetailsActivitiy extends Activity {
    private ListView listView;
    private ArrayList<String> list;
    private ShoppingDetailsAdapter adapter;
    private final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_details);
        Bundle extras = getIntent().getExtras();
        listView = findViewById(R.id.idDetailsListView);
        list = new ArrayList<>();
        list = extras.getStringArrayList("LISTE");
        adapter = new ShoppingDetailsAdapter(this,list, listView);
        listView.setAdapter(adapter);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(action.equals("ENDACTIVITY"))
                    finish();
            }
        };
        registerReceiver(broadcastReceiver,new IntentFilter("ENDACTIVITY"));
    }

    public void id_btn_retour_clicked(View view){
        Intent intent = new Intent(this, ShoppingActivity.class);
        intent.putExtra("LISTE",list);
        startActivity(intent);
    }
}
