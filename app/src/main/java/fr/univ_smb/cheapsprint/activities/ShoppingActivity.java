package fr.univ_smb.cheapsprint.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Locale;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.adapters.ShoppingAdapter;
import fr.univ_smb.cheapsprint.utilities.FileSaveHandler;

public class ShoppingActivity extends Activity {
    private ListView listView;
    private ArrayList<String> list;
    private ShoppingAdapter adapter;
    private static final String VALIDER_PROD = "valide";
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        Bundle extras = getIntent().getExtras();
        listView = findViewById(R.id.idShoppingListView);
        list = new ArrayList<>();
        if(extras != null) {
            if(extras.getStringArrayList("LISTE") != null)
                list = extras.getStringArrayList("LISTE");
            //LISTE fait référence à la liste de produits si on vient de ShoppingDetailsActivity

            String nomListe = extras.getString("NOMLISTE");
            //NOMLISTE fait référence au nom de la liste si on vient de
            //                                               ActivityMyLists/ShoppingListsAdapter

            if (nomListe != null && !nomListe.equals("") && FileSaveHandler.isFilePresent(context, nomListe)) {
                String[] mots = FileSaveHandler.read(context, nomListe).split("\"");
                for (int i = 1; i < mots.length - 1; i++)
                    if (!mots[i].equals(","))
                        list.add(mots[i]);
            }
        }
        adapter = new ShoppingAdapter(this,list, listView);
        listView.setAdapter(adapter);
        Intent intent = new Intent("ENDACTIVITY");
        sendBroadcast(intent);
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

    public void btn_shopping_micro_clicked(View view) {
        //https://stacktips.com/tutorials/android/speech-to-text-in-android
        startVoiceInput();
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Dites-nous ce que vous voulez acheter !");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //traitement texte
                    if(result.get(0).length() > 0) {
                        traitementTexte(result);
                        listView.setAdapter(adapter);
                    }
                }
                break;
            }
        }
    }

    private void traitementTexte(ArrayList<String> result){
        String[] mots = result.get(0).split(" ");
        StringBuilder produit = new StringBuilder();
        for (String s : mots){
            if((produit.length() == 0) && !s.equals(VALIDER_PROD)){
                produit.append(s).append(" ");
            }
            else if(produit.length() > 0){
                if(s.equals(VALIDER_PROD)){
                    list.add(produit.toString());
                    produit = new StringBuilder();
                }
                else{
                    produit.append(s).append(" ");
                }
            }
        }
        if((produit.length() > 0) && !produit.toString().equals(VALIDER_PROD))
            list.add(produit.toString());
    }

    public void btn_shopping_save_clicked(View view){
        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Nommez votre liste")
                .setMessage("Veuillez donner un nom à votre liste pour pouvoir la retrouver :")
                .setView(taskEditText)
                .setPositiveButton("Sauvegarder", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nomNouvelleListe = String.valueOf(taskEditText.getText());
                        //list to json -> folder to make it persistent and retrievable in MyListsActivity
                        String jsonList = new Gson().toJson(list);
                        boolean isFileCreated = FileSaveHandler.create(context, nomNouvelleListe, jsonList);
                        if (isFileCreated && FileSaveHandler.isFilePresent(context, nomNouvelleListe)) {
                            Toast.makeText(getApplication(), "Liste " + nomNouvelleListe + " sauvegardée !", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplication(), "Erreur lors de la sauvegarde.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Annuler", null)
                .create();
        dialog.show();
    }

    public void btn_shopping_details_clicked(View view){
        //TODO : 2 prochaines lignes à supprimer après tests, c'est pas ici qu'on veut ça mais dans ShoppingDetailsAdapter

        Intent intent = new Intent(this, ShoppingDetailsActivitiy.class);
        intent.putExtra("LISTE", list);
        startActivity(intent);
    }

}
