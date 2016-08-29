package com.isoftstone.rxjavademo.app;

import android.app.Application;

import com.isoftstone.rxjavademo.activity.dagger.LoginComponent;
import com.isoftstone.rxjavademo.activity.dagger.LoginModule;
import com.isoftstone.rxjavademo.app.user.UserComponent;
import com.isoftstone.rxjavademo.app.user.UserModule;
import com.isoftstone.rxjavademo.beans.BusProvider;
import com.isoftstone.rxjavademo.beans.ErrorBean;
import com.isoftstone.rxjavademo.beans.result.SysUserResponseVo;
import com.isoftstone.rxjavademo.view.LoginView;
import com.squareup.otto.Subscribe;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.app
 *
 * @Author: xie
 * @Time: 2016/8/18 10:21
 * @Description:
 */

public class MyApplication extends Application {
    private AppComponent appComponent;
    private UserComponent userComponent;
    private static MyApplication application;

    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = MyApplication.this;
        SingleBeans.getSingleBeans(this);
        BusProvider.getInstance().register(this);
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public UserComponent createUserComponent(SysUserResponseVo user) {
        userComponent = appComponent.getUserComponent(
                new UserModule(user));
        return userComponent;
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }

    public LoginComponent creatLoginComponent(LoginView loginView) {
        return appComponent.getUserComponent(new UserModule()).getLoginComponent(new LoginModule(loginView));
    }

    public void releaseUserComponent() {
        userComponent = null;
    }

    @Subscribe
    public void callback(ErrorBean error) {
        if (error != null) {
            SingleBeans.getToastor().showSingletonToast(error.getError());
        }
    }


}
