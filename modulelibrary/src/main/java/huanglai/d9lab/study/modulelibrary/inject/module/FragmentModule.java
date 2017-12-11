package huanglai.d9lab.study.modulelibrary.inject.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import huanglai.d9lab.study.modulelibrary.inject.scope.FragmentScope;

/**
 * Created by quantan.liu on 2017/3/21.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }

}
