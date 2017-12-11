package huanglai.d9lab.study.modulelibrary.inject.component;


import javax.inject.Singleton;

import dagger.Component;
import huanglai.d9lab.study.modulelibrary.app.Module;
import huanglai.d9lab.study.modulelibrary.inject.module.AppModule;

/**
 * Created by quantan.liu on 2017/3/21.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Module getContext();  // 提供App的Context

}
