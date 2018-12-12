package fr.univ_smb.cheapsprint.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.adapters.ShoppingDetailsAdapter;
import fr.univ_smb.cheapsprint.tasks.ScanListTask;

public class ShoppingDetailsActivitiy extends Activity {
    private ListView listView;
    private ArrayList<String> list;
    private ArrayList<JsonObject> listJson;
    private ShoppingDetailsAdapter adapter;
    private final Context context = this;
    private JsonArray listResultJson = new JsonArray();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_details);
        Bundle extras = getIntent().getExtras();
        listView = findViewById(R.id.idDetailsListView);
        list = new ArrayList<>();
        listJson = new ArrayList<>();
        list = extras.getStringArrayList("LISTE");

        // Envoie de la liste
        String jsonList = new Gson().toJson(list);
        Log.i("jsonList", jsonList);
        (new ScanListTask(context, jsonList, this)).execute();


        //


        // Adapter
        adapter = new ShoppingDetailsAdapter(this, listJson, listView);
        //listView.setAdapter(adapter);

        //
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

    public void updateList() {
        for (int i = 0; i < listResultJson.size(); i++) {
            listJson.add(listResultJson.get(i).getAsJsonObject());
        }
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Log.i("updatelist", String.valueOf(list));
    }

    public JsonArray getListResultJson() {
        return listResultJson;
    }

    public void setListResultJson(JsonArray listResultJson) {
        this.listResultJson = listResultJson;
    }
}
