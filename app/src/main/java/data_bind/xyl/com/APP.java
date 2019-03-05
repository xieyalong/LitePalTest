package data_bind.xyl.com;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import org.litepal.LitePal;

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        registerActivity();////
    }
    public  void registerActivity(){
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
                System.out.println(">]Activity="+activity.getClass().getName());
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {//监控进入和离开的状态
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }
}
