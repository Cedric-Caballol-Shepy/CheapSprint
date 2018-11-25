package fr.univ_smb.cheapsprint.tasks;

import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        String url = "http://80.211.56.41:8008/?api=prices/cheap";
        HttpPost request = new HttpPost(url);
        StringEntity param = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        JSONObject json = new JSONObject();
        try {
            json.put("data", data);
            param = new StringEntity("data" + data, "utf-8");
            Log.i("data", json.toString());
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(param);
            BasicResponseHandler handler = new BasicResponseHandler();
            String response = httpClient.execute(request, handler);
            Log.e("response", response);
            return response;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*String url = "http://80.211.56.41:8008/?api=prices/cheap";
        Map<String, String> param = new HashMap();
        param.put("data", data);
        JSONObject parameters = new JSONObject(param);
        try {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                    (Request.Method.POST, url, parameters.toJSONArray(parameters.names()), new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.e("response", response.toString());
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("error", error.getMessage());
                                }
                            });
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
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
        Log.e("result", result);
    }
}
