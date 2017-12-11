package huanglai.d9lab.study.modulelibrary.inject.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import huanglai.d9lab.study.modulelibrary.inject.scope.ActivityScope;

/**
 * Created by quantan.liu on 2017/3/21.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }

}
