package com.pers.myc.jsonuntil;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/13.
 */

public class JsonUntil {
    public static Object analysis(String response, Class aimClass) {

        //json类
        JSONObject snap;
        //对象数组
        Map<String, Field> fields;
        //对象变量名
        String[] fieldsName;
        //返回对象
        Object result;


        //初始化json类
        JSONTokener jsonTokener = new JSONTokener(response);
        snap = null;
        try {
            snap = (JSONObject) jsonTokener.nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //初始化返回类
        result = null;
        try {
            result = aimClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //设置对象变量值
        Field[] fd = aimClass.getDeclaredFields();
        fields = new HashMap<>();
        fieldsName = new String[fd.length - 2];

        for (int i = 0; i < fd.length - 2; i++) {
            fieldsName[i] = fd[i].getName();
            fields.put(fd[i].getName(), fd[i]);
        }

        //给result对象赋值
        assignment(result, snap);

        return result;
    }

    //赋值
    private static void assignment(Object aimObject, JSONObject jsonText) {
        //获取赋值对象的类
        Class objectClass = aimObject.getClass();
        //获取赋值对象的类的变量数组
        Field[] fields = objectClass.getDeclaredFields();
        //获取赋值对象的类的变量数组的长度
        int length = fields.length;
        //循环判断每个变量类型并赋值
        for (int i = 0; i < length; i++) {
            //所判断的变量
            Field field = fields[i];
            //变量名
            String name = field.getName();
            //获取变量类型
            if (field.getType().getName().toString().equals("java.lang.String")) {
                try {
                    //赋值
                    field.set(aimObject, jsonText.getString(name));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (field.getType().getName().toString().equals("java.util.List")) {
                //列表类型对象的范型类型
                String genericType = field.getGenericType().toString();
                //范型的类名
                genericType = genericType.substring(genericType.lastIndexOf("<") + 1, genericType.lastIndexOf(">"));
                if (genericType.equals("java.lang.String")) {
                    //范型类型为String时
                    try {
                        //赋值
                        JSONArray list = jsonText.getJSONArray(name);
                        List newList = new ArrayList();
                        for (int j = 0; j < list.length(); j++) {
                            newList.add(list.get(j));
                        }
                        field.set(aimObject, newList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    //范型类型为其内部类时
                    try {
                        //范型名
                        String className = genericType;
                        //内部类数组
                        Class[] declaredClasses = objectClass.getDeclaredClasses();
                        //内部类
                        Class declaredClass = null;
                        //json数组
                        JSONArray objArray = jsonText.getJSONArray(name);
                        for (int j = 0; j < declaredClasses.length; j++) {
                            if (declaredClasses[j].getName().toString().equals(className)) {
                                declaredClass = declaredClasses[j];
                            }
                        }
                        if (declaredClass != null && objArray != null) {
                            ArrayList newList = new ArrayList();
                            for (int j = 0; j < objArray.length(); j++) {
                                JSONObject jsonObject = (JSONObject) objArray.get(j);
                                Constructor cons = declaredClass.getDeclaredConstructor(aimObject.getClass());
                                Object object = cons.newInstance(aimObject);
                                assignment(object, jsonObject);
                                newList.add(object);
                            }
                            field.set(aimObject, newList);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (field.toString().indexOf("static") == -1 && field.toString().indexOf("final") == -1) {
                    try {
                        //变量类名
                        String className = field.getType().getName();
                        //内部类数组
                        Class[] declaredClasses = objectClass.getDeclaredClasses();
                        //内部类
                        Class declaredClass = null;
                        //json类
                        JSONObject jsonObject = jsonText.getJSONObject(name);
                        for (int j = 0; j < declaredClasses.length; j++) {
                            if (declaredClasses[j].getName().toString().equals(className)) {
                                declaredClass = declaredClasses[j];
                            }
                        }
                        Constructor cons = declaredClass.getConstructor(aimObject.getClass());
                        Object object = cons.newInstance(aimObject);
                        assignment(object, jsonObject);
                        field.set(aimObject, object);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void print(Object obj) {
        Log.e("msg: ", obj + "");
    }
}
