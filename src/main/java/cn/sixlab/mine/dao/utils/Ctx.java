package cn.sixlab.mine.dao.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

@Component
public class Ctx {
    private static ApplicationContext ctx;

    @Autowired
    public void setCtx(ApplicationContext ctx) {
        Ctx.ctx = ctx;
        System.out.println(ctx);
    }

    public static <T> Map<String, T> getBeans(Class<T> clz) {
        return ctx.getBeansOfType(clz);
    }

    public static <T> T getBean(Class<T> clz) {
        return ctx.getBean(clz);
    }

    public static <T> T getBean(Class<T> clz, String componentName) {

        return ctx.getBean(componentName, clz);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clz) {

        return ctx.getBeansOfType(clz);
    }

    public static <T> T getRandomBean(Class<T> clz) {
        Map<String, T> beans = ctx.getBeansOfType(clz);
        Collection<T> values = beans.values();
        if (values.size() > 0) {
            ArrayList<T> list = new ArrayList<>(values);
            return list.get(new Random().nextInt(list.size()));
        }
        return null;
    }

    public static String getProfile() {
        String[] profiles = ctx.getEnvironment().getActiveProfiles();

        if (null != profiles && profiles.length != 0) {
            return profiles[0];
        }

        profiles = ctx.getEnvironment().getDefaultProfiles();
        if (null != profiles && profiles.length != 0) {
            return profiles[0];
        }

        throw new IllegalStateException("Must specify a spring profile in the environment!");
    }
}
