package fr.univ_smb.cheapsprint.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.helpers.ViewHolder;

public class ShoppingAdapter extends ArrayAdapter<String> {
     private ArrayList<String> list;
     private ListView listView;
     private Context context;
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param objects The resource ID for a layout file containing a TextView to use when
     */
    public ShoppingAdapter(Context context, List<String> objects, ListView listView) {
        super(context, 0, objects);
        this.listView = listView;
        this.context = context;
        this.list = (ArrayList<String>) objects;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get text from his position
        String str = getItem(position);
        // Get the edit text produit
        ViewHolder holder = new ViewHolder();
        //EditText editText;
        //Button button;

        // Bind item with item view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_shopping, parent, false);
            holder.editText = convertView.findViewById(R.id.idItemShoppingTextProduit);
            holder.button = convertView.findViewById(R.id.idItemShoppingButton);
            //convertView.setTag(R.id.idItemShoppingTextProduit, editText);
            //convertView.setTag(R.id.idItemShoppingButton, button);
            convertView.setTag(holder);
            //convertView.setTag(button);
        } else {
            holder = (ViewHolder) convertView.getTag();
            //editText = (EditText) convertView.getTag(R.id.idItemShoppingTextProduit);
            //button = (Button) convertView.getTag(R.id.idItemShoppingButton);
        }


        // Set the produit in the edit text
        holder.editText.setText(str);
        holder.editText.setId(position);

        final ShoppingAdapter adapter = this;

        final EditText finalEditText = holder.editText;
        holder.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    String s = finalEditText.getText().toString();
                    Log.i("item", s);
                    Log.i("position", String.valueOf(position));
                    list.set(position, s);
                    Log.e("Liste", list.toString());
                    //finalEditText.requestFocus();

                    //listView.setAdapter(adapter);
                }
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.remove(position);
                listView.setAdapter(adapter);
            }
        });

        return convertView;
    }
}
