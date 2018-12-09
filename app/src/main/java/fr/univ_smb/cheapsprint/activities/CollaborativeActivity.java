package fr.univ_smb.cheapsprint.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.adapters.CollaborativeAdapter;
import fr.univ_smb.cheapsprint.models.Product;

public class CollaborativeActivity extends Activity {

    private ListView listView;

    private AutoCompleteTextView magasin,nomProd; //TODO : si on a le temps, faire la logique d'auto-complétion par rapport aux entrées dans la BDD
    private EditText prix;

    //https://stackoverflow.com/questions/4540754/dynamically-add-elements-to-a-listview-android
    //LIST OF ARRAY Product WHICH WILL SERVE AS LIST ITEMS
    ArrayList<Product> listItems=new ArrayList<Product>();

    //DEFINING A Product ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<Product> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaborative);
        prix = findViewById(R.id.idPrice);
        magasin = findViewById(R.id.idCollabSearchShop);
        nomProd = findViewById(R.id.idSearchProduct);
        listView = findViewById(R.id.idListViewAdded);
        adapter = new CollaborativeAdapter(this, listItems,listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(CollaborativeActivity.this);
                adb.setTitle("Supprimer ?");
                adb.setMessage("Voulez-vous vraiment supprimer cet élément ?");
                final int positionToRemove = position;
                adb.setNegativeButton("Non", null);
                adb.setPositiveButton("Oui", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        listItems.remove(positionToRemove);
                        adapter.notifyDataSetChanged();
                    }
                });
                adb.show();
            }
        });

    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void add_item(View v) {
        if(!prix.getText().toString().equals("") && !nomProd.getText().toString().equals("")) {
            listItems.add(new Product(nomProd.getText().toString(), Double.parseDouble(prix.getText().toString())));
            adapter.notifyDataSetChanged();
            nomProd.setText("");
            prix.setText("");
        }
        else{
            Toast.makeText(this, "Erreur : Remplissez tous les champs.", Toast.LENGTH_SHORT).show();
        }
    }

    public void submit_product_to_database(View v){
        if(!listItems.isEmpty() && !magasin.getText().toString().equals("")){
            //TODO : ajouter la liste de Product "listItems" dans la BDD
        }
        else{
            Toast.makeText(this, "Erreur : Ajoutez des produits dans la liste ainsi qu'un magasin.", Toast.LENGTH_SHORT).show();
        }
    }

}
