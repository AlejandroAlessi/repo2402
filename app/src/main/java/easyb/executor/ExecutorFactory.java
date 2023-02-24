package easyb.executor;

import easyb.executor.impl.ConcurrentNonBlockingExcerciseExecutor;

public class ExecutorFactory {

    public static ExcerciseAppExecutor getExecutor() {
        return new ConcurrentNonBlockingExcerciseExecutor();
    }

}
