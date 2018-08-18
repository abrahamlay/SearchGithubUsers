package com.abrahamlay.searchgithubusers.util.api;

/**
 * Created by abrahamlay on 15/08/2018.
 */

public class RemoteCallback {
    public interface Load<T> {
        void onDataLoaded(T data);

//        void onFailed(Throwable throwable);
//
//        void onDataNotAvailable(String message);
    }

    public interface Save<T> {
        void onDataSaved(T data);

        void onSaveFailed(Throwable throwable);
    }

    public interface Update<T> {
        void onDataUpdated(T data);

        void onUpdateFailed(Throwable throwable);
    }

    public interface Delete<T> {
        void onDataDeleted(T data);

        void onDeleteFailed(Throwable throwable);
    }
}
