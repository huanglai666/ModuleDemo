package huanglai.d9lab.study.modulelibrary.inject.component;

import android.app.Activity;

import dagger.Component;
import huanglai.d9lab.study.modulelibrary.inject.module.FragmentModule;
import huanglai.d9lab.study.modulelibrary.inject.scope.FragmentScope;

/**
 * Created by quantan.liu on 2017/3/21.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

}
