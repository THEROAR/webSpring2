package com.example;

import java.lang.reflect.Field;

public class Test implements TestInterface {

    public String getReflectField() {
        try {
            Class userClass = Class.forName("com.example.User");
            Field[] fields = userClass.getDeclaredFields();
            for (Field field : fields) {
                try {
                    Object object = field.getType().newInstance();

                    if (object instanceof String) {
                        return object.toString();
                    }
                }  catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
