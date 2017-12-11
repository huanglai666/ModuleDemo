package huanglai.d9lab.study.moduledemo;

import android.app.Application;

import huanglai.d9lab.study.modulelibrary.app.Module;

/**
 * Created by 黄濑 on 2017/12/11.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Module.init(this);
    }
}
