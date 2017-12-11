package huanglai.d9lab.study.modulelibrary.base.ui;

import android.os.Bundle;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import huanglai.d9lab.study.modulelibrary.base.BasePresenter;
import huanglai.d9lab.study.modulelibrary.http.Stateful;
import huanglai.d9lab.study.modulelibrary.view.LoadingPage;

/**
 * Created by quantan.liu on 2017/3/27.
 */

/*
 我的笔记
    1.继承BaseActivity，实现了Stateful接口用来设置当前的状态，状态包括这个Activity中包含的LoadingPage的几种状态，在接口的具体实现中也是为LoadingPage去设定状态
    2.使用泛型，在这个类里包含有一个泛型的P实例，类型为BasePresenter的子类，针对P实例使用了@Inject注解实现注入，并申明了抽象方法initInject()，这意味着所有的子类都需要实现这个方法，来完成具体的注入操作
    3.获取子类的FrameLayout的Id作为最顶层，将LoadingPage添加进去
*/

public abstract class LoadingBaseActivity<T extends BasePresenter> extends BaseActivity implements Stateful {

    protected LoadingPage mLoadingPage;//一个实现了冲成功加载界面，加载中，加载失败，空界面的FrameLayout
    @Inject
    protected T mPresenter;//使用Dagger2注入的P层实例，类型为BasePresenter的子类
    private Unbinder bind;//用于解绑Butterknife绑定的内容的
    protected FrameLayout flBaseContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initInject();//实现注入的代码
        mPresenter.attachView(this);
        flBaseContent = (FrameLayout) findViewById(setFrameLayoutId());
        if (mLoadingPage == null) {
            mLoadingPage = new LoadingPage(this) {
                @Override
                protected void initView() {
                    bind = ButterKnife.bind(LoadingBaseActivity.this, contentView);
                    LoadingBaseActivity.this.initView();
                }

                @Override
                protected void loadData() {
                    LoadingBaseActivity.this.loadData();
                }

                @Override
                protected int getLayoutId() {
                    return LoadingBaseActivity.this.getContentLayoutId();
                }
            };
        }
        flBaseContent.addView(mLoadingPage);
        loadData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }

    @Override
    public void setState(int state) {
        mLoadingPage.state = state;
        mLoadingPage.showPage();
    }


    /**
     * 1
     * 根据网络获取的数据返回状态，每一个子类的获取网络返回的都不一样，所以要交给子类去完成
     * * 如果是静态页面不需要网络请求的在子类的loadData方法中添加以下2行即可
     * mLoadingPage.state = STATE_SUCCESS;
     * mLoadingPage.showPage();
     * 或者调用setState(AppConstants.STATE_SUCCESS)
     */
    protected abstract void loadData();

    /**
     * 2
     * 网络请求成功在去加载布局
     *
     * @return
     */
    public abstract int getContentLayoutId();

    /**
     * 3
     * 子类关于View的操作(如setAdapter)都必须在这里面，会因为页面状态不为成功，而binding还没创建就引用而导致空指针。
     * loadData()和initView只执行一次，如果有一些请求需要二次的不要放到loadData()里面。
     */
    protected abstract void initView();

    /**
     * dagger2注入
     */
    protected abstract void initInject();


    //子类只需要实现下面两个方法 孙子类需要实现所有。子类还需要实现BaseActivity的getLayoutId();

    /**
     * 获取子类FrameLayout的Id是孙子类的容器
     *
     * @return
     */
    public abstract int setFrameLayoutId();

    /**
     * 用于子类初始化findviewbyid的。
     * 这里初始化的Id是为了子类能公用的。
     */
    protected abstract void initUI();

}
