package huanglai.d9lab.study.modulelibrary.inject.component;

import android.app.Activity;

import dagger.Component;
import huanglai.d9lab.study.modulelibrary.inject.module.ActivityModule;
import huanglai.d9lab.study.modulelibrary.inject.scope.ActivityScope;

/**
 * Created by quantan.liu on 2017/3/21.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
}
