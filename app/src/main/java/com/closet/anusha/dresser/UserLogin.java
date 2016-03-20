package com.closet.anusha.dresser;
        import com.kinvey.android.Client;
        import android.support.multidex.MultiDexApplication;

public class UserLogin extends MultiDexApplication {
    private Client service;

    // Application Constants
    public static final String AUTHTOKEN_TYPE = "com.kinvey.myapplogin";
    public static final String ACCOUNT_TYPE = "com.kinvey.myapplogin";
    public static final String LOGIN_TYPE_KEY = "loginType";

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }


    private void initialize() {
        // Enter your app credentials here
        service = new Client.Builder(this).build();
    }

    public Client getKinveyService() {
        return service;
    }
}
