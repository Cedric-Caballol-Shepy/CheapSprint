package fr.univ_smb.cheapsprint.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;


import java.util.List;

import fr.univ_smb.cheapsprint.R;

public class ShoppingAdapter extends ArrayAdapter<String> {

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param objects The resource ID for a layout file containing a TextView to use when
     */
    public ShoppingAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get text from his position
        String str = getItem(position);

        // Bind item with item view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_shopping, parent, false);
        }

        // Get the edit text produit
        EditText editText =convertView.findViewById(R.id.idItemShoppingTextProduit);
        // Set the produit in the edit text
        editText.setText(str);
        return convertView;
    }
}
