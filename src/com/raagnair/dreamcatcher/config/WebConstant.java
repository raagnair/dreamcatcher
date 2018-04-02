package com.raagnair.dreamcatcher.config;

import com.raagnair.dreamcatcher.rest.UserAPI;

public enum WebConstant
{
    PORT("PORT", "8080"),
    DIRECTORY_WEBAPP("DIRECTORY_WEBAPP", "frontend"),
    PATH_REST("PATH_REST", "/rest/*"),
    PATH_HTML("PATH_HTML", "/*"),
    PACKAGE_REST(
            "PACKAGE_REST",
            UserAPI.class.getPackage()
                    .toString());

    public final String varName;
    public final String varDefault;

    WebConstant(String var, String defValue)
    {
        this.varName = var;
        this.varDefault = defValue;
    }
}
