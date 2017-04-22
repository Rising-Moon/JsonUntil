package com.pers.myc.jsonuntil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    String test1 = "{\n" +
            "  \"username\": \"Dark\",\n" +
            "  \"clientToken\": \"f66ff127-bbaf-413d-a414-80d429afe55b\",\n" +
            "  \"enableShadow\": false,\n" +
            "  \"aa\": [\n" +
            "    \"http://taojinbi.taobao.com?tracelogww=wwqz&tracelogw=wwapp_quanbu\",\n" +
            "    \"http://img01.taobaocdn.com:80/tfscom/TB16dbjKXXXXXbIXVXXXXXXXXXX\"\n" +
            "  ],\n" +
            "  \"theme\": 2,\n" +
            "  \"a\": [\n" +
            "    {\n" +
            "      \"ver\": \"1.8.0_102\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"ver\": \"1.8.0_121\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    String test2 = "{\n" +
            "  \"username\": \"OneShot\",\n" +
            "  \"clientToken\": \"52e6f427-185e-4d36-b7b7-c2158418f712\",\n" +
            "  \"enableShadow\": true,\n" +
            "  \"theme\": 0,\n" +
            "  \"java\": [\n" +
            "    {\n" +
            "      \"ver\": \"1.8.0_102\",\n" +
            "      \"platform\": 2,\n" +
            "      \"location\": \"E:\\\\Android\\\\java\\\\bin\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"ver\": \"1.8.0_121\",\n" +
            "      \"platform\": 2,\n" +
            "      \"location\": \"C:\\\\Program Files\\\\Java\\\\jre1.8.0_121\\\\bin\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"logintype\": 0,\n" +
            "  \"downloadtype\": 0,\n" +
            "  \"configurations\": {\n" +
            "    \"Default\": {\n" +
            "      \"name\": \"Default\",\n" +
            "      \"selectedMinecraftVersion\": \"1.7.10-Forge10.13.4.1448-1.7.10\",\n" +
            "      \"minecraftArgs\": \"\",\n" +
            "      \"gameDir\": \".\\\\.minecraft\",\n" +
            "      \"javaDir\": \"\",\n" +
            "      \"precalledCommand\": \"\",\n" +
            "      \"serverIp\": \"\",\n" +
            "      \"java\": \"Default\",\n" +
            "      \"fullscreen\": false,\n" +
            "      \"debug\": false,\n" +
            "      \"noJVMArgs\": false,\n" +
            "      \"canceledWrapper\": false,\n" +
            "      \"launcherVisibility\": 0,\n" +
            "      \"gameDirType\": 0\n" +
            "    }\n" +
            "  },\n" +
            "  \"yggdrasil\": {}\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Test2 t2 = (Test2) JsonUntil.analysis(test2, Test2.class);

        print("username:" + t2.username + "\n" +
                "java:" + "\n" +
                "--ver" + t2.java.get(0).ver + "\n" +
                "configurations:" + t2.configurations + "\n" +
                "--Default:" + t2.configurations.Default + "\n" +
                "----name:" + t2.configurations.Default.name + "\n" +
                "yggdrasil:" + t2.yggdrasil);
    }

    public static void print(Object obj) {
        Log.e("msg: ", obj + "");
    }
}
