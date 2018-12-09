package fr.univ_smb.cheapsprint.adapters;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.helpers.ViewHolderShoppingDetails;
import fr.univ_smb.cheapsprint.models.Product;

public class CollaborativeAdapter  extends ArrayAdapter<Product> {
    private ArrayList<Product> list;
    private ListView listView;
    private Context context;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param objects The resource ID for a layout file containing a TextView to use when
     */
    public CollaborativeAdapter(Context context, List<Product> objects, ListView listView) {
        super(context, 0, objects);
        this.listView = listView;
        this.context = context;
        this.list = (ArrayList<Product>) objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get text from his position
        Product prod = getItem(position);
        // Get the edit text produit (re-using ViewHolderShoppingDetails)
        ViewHolderShoppingDetails holder = new ViewHolderShoppingDetails();

        // Bind item with item view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_collaborative, parent, false);
            holder.product = convertView.findViewById(R.id.idProduct);
            holder.price = convertView.findViewById(R.id.idPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderShoppingDetails) convertView.getTag();
        }

        // Set the product and price in the text
        holder.product.setText(prod.getName());
        holder.product.setId(position);
        holder.price.setText(String.format("%s€", String.valueOf(prod.getPrice())));
        holder.price.setId(position);

        return convertView;
    }
}
