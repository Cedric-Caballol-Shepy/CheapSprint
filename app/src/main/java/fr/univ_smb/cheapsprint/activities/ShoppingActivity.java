package fr.univ_smb.cheapsprint.activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.adapters.ShoppingAdapter;


/**
 *
 */
public class ShoppingActivity extends Activity {
    private ListView listView;
    private ArrayList<String> list;
    private static final String VALIDER_PROD = "valide";
    private static final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        listView = findViewById(R.id.idShoppingListView);
        list = new ArrayList<>();
        listView.setAdapter(new ShoppingAdapter(this, list,listView));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public void btn_shopping_micro_clicked(View view) {
        //https://stacktips.com/tutorials/android/speech-to-text-in-android
        startVoiceInput();
        Log.i("test :", "test");
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
                        listView.setAdapter(new ShoppingAdapter(this, list,listView));
                    }
                }
                break;
            }
        }
    }

    private void traitementTexte(ArrayList<String> result){
        String[] mots = result.get(0).split(" ");
        String produit = new String();
        for (String s : mots) {
            if (s.equals(VALIDER_PROD) && !s.isEmpty()) {
                list.add(produit);
                produit = "";
            }
            else {
                if(!s.isEmpty())
                    produit += " " + s;
            }
        }
        list.add(produit);
    }
}
