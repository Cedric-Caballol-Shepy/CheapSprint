package fr.univ_smb.cheapsprint.adapters;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.activities.ShoppingActivity;
import fr.univ_smb.cheapsprint.helpers.ViewHolder;
import fr.univ_smb.cheapsprint.utilities.FileSaveHandler;

public class ShoppingListsAdapter extends ArrayAdapter<String> {
    private ArrayList<String> list;
    private ListView listView;
    private Context context;
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param objects The resource ID for a layout file containing a TextView to use when
     */
    public ShoppingListsAdapter(Context context, List<String> objects, ListView listView) {
        super(context, 0, objects);
        this.listView = listView;
        this.context = context;
        this.list = (ArrayList<String>) objects;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get text from his position
        final String str = getItem(position);
        // Get the edit text produit
        ViewHolder holder = new ViewHolder();

        // Bind item with item view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_lists_shopping, parent, false);

            holder.textView = convertView.findViewById(R.id.idItemListsShoppingTextList);
            holder.button = convertView.findViewById(R.id.idItemListsShoppingButton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.textView.setText(str); //to set list name
        holder.textView.setId(position);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder confirmation = new AlertDialog.Builder(context);
                confirmation.setPositiveButton("Oui", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Log.i("bool deleted : ",""+FileSaveHandler.removeFile(context,list.get(position)));
                        list.remove(position);
                        listView.setAdapter(new ShoppingListsAdapter(context, list, listView));
                    }
                });
                confirmation.setNegativeButton("Non", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {/*rien à faire ici*/}
                });

                confirmation.setMessage("Voulez-vous vraiment supprimer cette liste ?");
                confirmation.setTitle("Confirmation");

                AlertDialog d = confirmation.create();
                d.show();
            }
        });
        final ViewHolder finalHolder = holder;
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Nom liste : ", ""+finalHolder.textView.getText());
                Intent intent = new Intent(context, ShoppingActivity.class);
                intent.putExtra("NOMLISTE", str);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
