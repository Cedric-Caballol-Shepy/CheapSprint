package fr.univ_smb.cheapsprint.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import fr.univ_smb.cheapsprint.activities.ShoppingDetailsActivitiy;

public class ScanListTask extends AsyncTask<Void, Void, String> {
    private Context context;
    private String data;
    private ShoppingDetailsActivitiy shoppingDetailsActivitiy;
    /**
     * Runs on the UI thread before {@link #doInBackground}.
     *
     * @see #onPostExecute
     * @see #doInBackground
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public ScanListTask(Context context, String data, ShoppingDetailsActivitiy shoppingDetailsActivitiy) {
        this.context = context;
        this.data = data;
        this.shoppingDetailsActivitiy = shoppingDetailsActivitiy;
    }
    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */

    protected String doInBackground(Void... params) {
        String response = null;
        try {
            Log.i("asyncdatatest", this.data);
            URL url = new URL("http://80.211.56.41:8008/api/product?data=" + this.data);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.i("AsyncTask","Envoie requete GET");
            //conn.setDefaultUseCaches(false);
            conn.setConnectTimeout(5000);
            //conn.setRequestMethod("GET");
            conn.connect();
            Log.i("AsyncTask","Reception requete GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            Log.i("AsyncTask","Récupération Contenu InputStream");
            response = convertStreamToString(in);
            Log.w("AsyncTask data receve",response);
            conn.disconnect();
            return response;
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            Log.e("INTERNET","MalformedURLException: "+e.getMessage());
        } catch (ProtocolException e) {
            //e.printStackTrace();
            Log.e("INTERNET","ProtocolException: "+e.getMessage());
        } catch (IOException e) {
            //e.printStackTrace();
            Log.e("INTERNET","IOException: "+e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            //e.printStackTrace();
            Log.e("INTERNET","Exception: "+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param result The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result != null) {
            Log.e("resultAsync", result);
            this.shoppingDetailsActivitiy.setListResultJson((JsonArray) new JsonParser().parse(result));
            this.shoppingDetailsActivitiy.updateList();
        }
    }

    /**
     * Converti un InputStream en String
     * @param in
     * @return
     */
    public static String convertStreamToString(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line=reader.readLine()) != null){
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            //e.printStackTrace();
            Log.e("Lecture","Impossible");
        } finally {
            try {
                in.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
