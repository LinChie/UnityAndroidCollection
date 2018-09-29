package com.linyi.gamenotify;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.unity3d.player.UnityPlayerActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends UnityPlayerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
    }

    public void DisplayToast(final String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(MainActivity.this).setMessage(message).show();
            }
        });
    }

    public void Notify(String json){
        try{
            JSONObject jsonObject=new JSONObject(json);
            String message=jsonObject.getString("message");
            String title=jsonObject.getString("title");
            String channelId=jsonObject.getString("channelId");
            String channelName=jsonObject.getString("channelName");
            NotificationUtility notifyUtility=NotificationUtility.getInstance();
            notifyUtility.Notify(message,title,channelId,channelName,this);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
