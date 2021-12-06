package com.example.raisehands.BackgroundWorker;

import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.raisehands.user_dashboard;
import com.example.raisehands.user_donate;
import com.example.raisehands.user_login;
import com.example.raisehands.user_registration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class Backgroundworker extends AsyncTask<HashMap<String, String>, Void, String> {

    private user_donate ParentDo;
    private user_registration ParentR;
    private user_dashboard ParentD;
    private user_login ParentL;
    private String type = "";
    private Dialog myDialog;

    public Backgroundworker(user_login parent) {
        ParentL = parent;
        myDialog = new Dialog(ParentL);
    }

    public Backgroundworker(user_dashboard parent) {
        ParentD = parent;
        myDialog = new Dialog(ParentD);
    }

    public Backgroundworker(user_registration parent) {
        ParentR = parent;
        myDialog = new Dialog(ParentR);
    }

    public Backgroundworker(user_donate parent) {
        ParentDo = parent;
        myDialog = new Dialog(ParentDo);
    }

    @Override
    protected String doInBackground(HashMap<String, String>... params) {
        HashMap<String, String> param = params[0];
        type = param.get("type");
        String login_url = "http://192.168.1.4/RaiseHands/PHP/mobile.php";
        try {
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = "";
            for (String name : param.keySet()) {
                String key = name;
                String value = param.get(name).toString();
                post_data += URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8") + "&";
            }
            if (post_data.length() > 0) {
                post_data = post_data.substring(0, post_data.length() - 1);
            }
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.i("Error_test1", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("Error_test2", e.toString());
        } catch (Exception e) {
            Log.i("Error_test3", e.toString());
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
//        myDialog.setContentView(R.layout.activity_custome_pop_up);
//        ProgressBar progressBar = (ProgressBar) myDialog.findViewById(R.id.spin_kit);
//        Sprite wave = new Wave();
//        progressBar.setIndeterminateDrawable(wave);
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
//        myDialog.dismiss();
        if (type.equals("login")) {
            ParentL.displayName(result);
        }
        if (type.equals("load_projects")) {
            ParentD.displayName(result);
        }
        if (type.equals("addDonoor")) {
            ParentR.displayName(result);
        }
        if (type.equals("addDonation")) {
            ParentDo.displayName(result);
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
