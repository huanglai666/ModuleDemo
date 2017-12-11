package huanglai.d9lab.study.modulelibrary.inject.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quantan.liu on 2017/3/21.
 */
@Module
public class AppModule {

    private final Module application;

    public AppModule(Module application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Module provideApplicationContext() {
        return application;
    }
}
