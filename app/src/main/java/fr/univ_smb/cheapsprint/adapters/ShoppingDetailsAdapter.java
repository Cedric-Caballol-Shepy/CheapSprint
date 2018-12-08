package fr.univ_smb.cheapsprint.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.helpers.ViewHolderShoppingDetails;

public class ShoppingDetailsAdapter extends ArrayAdapter<String> {
    private ArrayList<String> list;
    private ListView listView;
    private Context context;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param objects The resource ID for a layout file containing a TextView to use when
     */
    public ShoppingDetailsAdapter(Context context, List<String> objects, ListView listView) {
        super(context, 0, objects);
        this.listView = listView;
        this.context = context;
        this.list = (ArrayList<String>) objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get text from his position
        String productName = getItem(position);
        // Get the edit text produit
        ViewHolderShoppingDetails holder = new ViewHolderShoppingDetails();

        // Bind item with item view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_shopping_details, parent, false);
            holder.product = convertView.findViewById(R.id.idProduct);
            holder.price = convertView.findViewById(R.id.idSearchPrice);
            holder.magasin = convertView.findViewById(R.id.idSearchMagasin);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderShoppingDetails) convertView.getTag();
        }

        // Set the produit in the edit text
        holder.product.setText(productName);
        holder.product.setId(position);

        //TODO : Ajouter récupération dans la BDD du prix et du magasin (correspondant au produit (variable "productName"))

        return convertView;
    }
}
