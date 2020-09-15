package com.example.dell.tollapplication;

import android.content.Context;

/**
 * Created by dell on 22-Feb-18.
 */
public class CheckValidations {

    public static boolean checkIsEmpty(Context context,String val)
    {
        if(val==null)
        {
            return  true;
        }

        if(val.length()==0)
        {
            return  true;
        }
        return false;

    }

    public static boolean checkPhone(Context context, String val)
    {
        if(val==null)
        {
            return true;
        }
        if(val.length()!=10 )
        {
            return true;
        }
        return false;
    }

    public static boolean checkEmail(Context context,String val)
    {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.matches(emailPattern) && val.length() > 0)
        {
            return false;
        }
        return true;
    }
}