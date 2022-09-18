package com.menash.springretrydemo.util.retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.interceptor.MethodInvocationRetryCallback;
import org.springframework.retry.listener.MethodInvocationRetryListenerSupport;
import org.springframework.stereotype.Component;

class RetryListener extends MethodInvocationRetryListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(RetryListener.class);

    @Override
    protected <T, E extends Throwable> boolean doOpen(RetryContext context,
                                                      MethodInvocationRetryCallback<T, E> callback) {
        log.info("Invocation of method: " + callback.getInvocation().getMethod().toGenericString()
                + " with label: " + callback.getLabel());
        return super.doOpen(context, callback);
    }

    @Override
    protected <T, E extends Throwable> void doOnError(RetryContext context, MethodInvocationRetryCallback<T, E> callback, Throwable throwable) {
        log.error("Exception Occurred, Retry Count {} ", context.getRetryCount());
        super.doOnError(context, callback, throwable);
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context,
                                               RetryCallback<T, E> callback, Throwable throwable) {


        log.error("Unable to recover from  Exception. Exception type:{}",throwable);
        log.error("Error ", throwable);
        super.close(context, callback, throwable);
    }

}