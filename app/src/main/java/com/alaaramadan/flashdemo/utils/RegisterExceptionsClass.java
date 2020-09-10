package com.alaaramadan.flashdemo.utils;


public class RegisterExceptionsClass {

    public static String EX = "Exception";
    public static String API = "ExceptionAPI";

//    public static void registerException(Exception e) {
//        StackTraceElement stackTraceElement = e.getStackTrace()[0];
//        String className = null, funcName = null, errorMessage = null;
//        int LineNumber = stackTraceElement.getLineNumber();
//        className = stackTraceElement.getClassName();
//        funcName = stackTraceElement.getMethodName();
//        errorMessage = e.getMessage();
//
//        send(LineNumber, className, funcName, errorMessage, EX);
//    }

//    public static void registerException(Throwable e) {
//        StackTraceElement stackTraceElement = e.getStackTrace()[0];
//        String className = null, funcName = null, errorMessage = null;
//        int LineNumber = stackTraceElement.getLineNumber();
//        className = stackTraceElement.getClassName();
//        funcName = stackTraceElement.getMethodName();
//        errorMessage = e.getMessage();
//
//        send(LineNumber, className, funcName, errorMessage, API);
//    }

//    private static void send(int lineNumber, String className, String funcName, String errorMessage, String ex) {
//        getClient().registerException(lineNumber, className, funcName, errorMessage, ex).subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableSingleObserver<NullResponse>() {
//                    @Override
//                    public void onSuccess(NullResponse auth) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });
//    }
}

