using LitJson;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Test : MonoBehaviour
{

    private void Start()
    {
    }

    public void DisplayToast()
    {
        using (AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
        {
            using (AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject>("currentActivity"))
            {
                jo.Call("DisplayToast", "测试Unity调用java方法");
            }
        }
    }

    public void TestNotify()
    {
        JsonData json = new JsonData();
        json["message"] = "推送成功了";
        json["title"] = "推送测试";
        json["channelId"] = "linyi";
        json["channelName"] = "推送";
        using (AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
        {
            using (AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject>("currentActivity"))
            {
                jo.Call("Notify", json.ToJson());
            }
        }
    }
}
