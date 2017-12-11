package huanglai.d9lab.study.modulelibrary.app;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 黄濑 on 2017/12/11.
 */

public class Module {
    private static Context appContext;

    public static void init(Context context) {
        if (null != context) {
            appContext = context;
        }
        doSomeThing();
    }

    private static void doSomeThing() {
        Toast.makeText(appContext, "Module加载完毕", Toast.LENGTH_LONG).show();
    }
}
