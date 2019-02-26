package com.goodlogic.a05_retrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import retrofit2.Call;
import retrofit2.Platform;

public class RealService implements GitHubService {

    InvocationHandler invocationHandler = new InvocationHandler() {
        private final Platform platform = Platform.get();
        private final Object[] emptyArgs = new Object[0];

        @Override public Object invoke(Object proxy, Method method, @Nullable Object[] args)
                throws Throwable {
            // If the method is a method from Object then defer to normal invocation.
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, args);
            }
            if (platform.isDefaultMethod(method)) {
                return platform.invokeDefaultMethod(method, service, proxy, args);
            }
            return loadServiceMethod(method).invoke(args != null ? args : emptyArgs);
        }
    };
    @Override
    public Call<List<Repo>> getRepos() {
        try {
            Method method = GitHubService.class.getMethod("getRepos");
            return (Call<List<Repo>>)invocationHandler.invoke(this,method,null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }finally {
            return null;
        }
    }
    @Override
    public Call<User> getUser() {
        try {
            Method method = GitHubService.class.getMethod("getUser");
            return (Call<User>)invocationHandler.invoke(this,method,null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }finally {
            return null;
        }
    }

}
