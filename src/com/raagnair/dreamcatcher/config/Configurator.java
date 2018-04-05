package com.raagnair.dreamcatcher.config;

public class Configurator
{
    public static String getProperty(WebConstant var)
    {
        return System.getProperty(var.varName, var.varDefault);
    }

    public static String getsEnvVariable(WebConstant var)
    {
        String envVar = System.getenv(var.varName);
        return envVar != null ? envVar : var.varDefault;
    }
}
