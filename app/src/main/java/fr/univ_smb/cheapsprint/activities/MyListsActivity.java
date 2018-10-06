package fr.univ_smb.cheapsprint.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.adapters.ShoppingAdapter;

public class MyListsActivity extends Activity {
    private ListView listView;
    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lists);
        listView = findViewById(R.id.idListsListView);
        list = new ArrayList<>();
        listView.setAdapter(new ShoppingAdapter(this, list,listView));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
