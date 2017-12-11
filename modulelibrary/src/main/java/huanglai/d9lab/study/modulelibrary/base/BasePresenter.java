package huanglai.d9lab.study.modulelibrary.base;


import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

import huanglai.d9lab.study.modulelibrary.app.AppConstants;
import huanglai.d9lab.study.modulelibrary.http.LifeSubscription;
import huanglai.d9lab.study.modulelibrary.http.Stateful;
import huanglai.d9lab.study.modulelibrary.http.utils.HttpUtils;
import rx.Observable;
import huanglai.d9lab.study.modulelibrary.http.utils.Callback;

/**
 * Created by quantan.liu on 2017/3/22.
 */

public class BasePresenter<T extends BaseView> {

    protected Reference<T> mReferenceView;//指的是界面，也就是BaseFragment或者BaseActivity

    protected T mView;
    private Callback callback;


    public void attachView(LifeSubscription mLifeSubscription) {
        /**使用弱引用将mView指向mLifeSubscription（实际是某个View，比如Activity），
         * 当Activity生命周期结束后，即mLifeSubscription被回收时，BasePresenter中的mView也会随之变为null
         * 这样保证了mView（仅在Activity存在时需要使用的）不会常驻内存*/
        this.mReferenceView = new WeakReference<>((T) mLifeSubscription);
        mView = mReferenceView.get();

    }

    protected <T> void invoke(Observable<T> observable, Callback<T> callback) {
        this.callback = callback;
        HttpUtils.invoke((LifeSubscription) mView, observable, callback);
    }

    /**
     * 给子类检查返回集合是否为空
     * 这样子做虽然耦合度高，但是接口都不是统一定的，我们没有什么更好的办法
     *
     * @param list
     */
    public void checkState(List list) {
        if (list.size() == 0) {
            if (mView instanceof Stateful)
                ((Stateful) mView).setState(AppConstants.STATE_EMPTY);
            return;
        }
    }


    //P层示例被销毁的时候调用
    public void detachView() {
        if (mReferenceView != null)
            mReferenceView.clear();
        mReferenceView = null;
        if (mView != null)
            mView = null;
        if (callback != null) {
            callback.detachView();
        }
    }
}
