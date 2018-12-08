package fr.univ_smb.cheapsprint.tasks;

import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class ScanListTask extends AsyncTask<Void, Void, String> {
    private Context context;
    private String data;

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

    public ScanListTask(Context context, String data){this.context = context; this.data = data;}
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
            URL url = new URL("https://raw.githubusercontent.com/sirambd/application_android/master/plombierDbbJson.json");
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
        if(result != null)
            Log.e("resultAsync", result);
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
