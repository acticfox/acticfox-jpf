package com.github.acticfox.extension.register;

import java.util.function.Consumer;
import java.util.function.Function;

import com.github.acticfox.extension.ExtensionCoordinate;
import com.github.acticfox.jpf.api.BizScenario;

/**
 * 扩展器执行器抽象
 * 
 * @Description: TODO
 * @author kfy Jun 21, 2022 2:01:01 PM
 * @version V1.0
 */
public abstract class AbstractComponentExecutor {

    /**
     * Execute extension with Response
     *
     * @param targetClz
     * @param bizScenario
     * @param exeFunction
     * @param <R> Response Type
     * @param <T> Parameter Type
     * @return
     */
    public <R, T> R execute(Class<T> targetClz, BizScenario bizScenario, Function<T, R> exeFunction) {
        T component = locateComponent(targetClz, bizScenario);
        return exeFunction.apply(component);
    }

    public <R, T> R execute(ExtensionCoordinate extensionCoordinate, Function<T, R> exeFunction) {
        return execute((Class<T>)extensionCoordinate.getExtensionPointClass(), extensionCoordinate.getBizScenario(),
            exeFunction);
    }

    /**
     * Execute extension without Response
     *
     * @param targetClz
     * @param context
     * @param exeFunction
     * @param <T> Parameter Type
     */
    public <T> void executeVoid(Class<T> targetClz, BizScenario context, Consumer<T> exeFunction) {
        T component = locateComponent(targetClz, context);
        exeFunction.accept(component);
    }

    public <T> void executeVoid(ExtensionCoordinate extensionCoordinate, Consumer<T> exeFunction) {
        executeVoid(extensionCoordinate.getExtensionPointClass(), extensionCoordinate.getBizScenario(), exeFunction);
    }

    protected abstract <C> C locateComponent(Class<C> targetClz, BizScenario context);
}
