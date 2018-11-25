package fr.univ_smb.cheapsprint.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.adapters.ShoppingListsAdapter;
import fr.univ_smb.cheapsprint.utilities.FileSaveHandler;

public class MyListsActivity extends Activity {
    private ListView listView;
    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lists);
        listView = findViewById(R.id.idListsListView);
        list = FileSaveHandler.ScanDirLists(this);
        listView.setAdapter(new ShoppingListsAdapter(this, list,listView));
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

    public void btn_my_lists_quit(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
