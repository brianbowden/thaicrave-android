package com.thaicrave.android.app;

public class TcEnv {

    public enum ENV {
        LOCAL,
        DEVELOPMENT,
        PRODUCTION
    }

    private static TcEnv env;

    public static TcEnv get() {
        if (env != null) return env;
        else throw new IllegalStateException("No environment selected");
    }

    public static void init(ENV environment) {
        env = new TcEnv(environment);
    }

    public final ENV ENVIRONMENT;
    public final String ROOT_API_URL;

    private TcEnv(ENV environment) {
        ENVIRONMENT = environment;

        if (ENVIRONMENT == ENV.LOCAL) {
            ROOT_API_URL = "http://192.168.56.1:60000/api/";
        } else {
            ROOT_API_URL = "";
        }
    }
}
