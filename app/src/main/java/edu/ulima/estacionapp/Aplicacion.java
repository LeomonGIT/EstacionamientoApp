package edu.ulima.estacionapp;

import android.app.Application;

import com.parse.Parse;

public class Aplicacion extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "M1eNpD5WHq4zNrPb5RcS1qebV2H1ycg5bQYAL6BQ", "6Bo2dRVxVXIySOWhtUWOK7LUXC8vhoiTjPBee491");

    }
}
