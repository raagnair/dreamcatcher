package com.raagnair.dreamcatcher.config;

public class Configurator
{
    public static String get(WebConstant var)
    {
        return System.getProperty(var.varName, var.varDefault);
    }

    public static String get(WebConstant var, String defaultValue)
    {
        return System.getProperty(var.varName, defaultValue);
    }

    public static String get(String var, String defaultValue)
    {
        return System.getProperty(var, defaultValue);
    }
}
