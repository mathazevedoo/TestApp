package com.location.inoarb.session;

import com.location.inoarb.singleton.impl.SessionSingleton;

public final class SessionAccess {

    private static final SessionSingleton sessionSingleton = SessionSingleton.getInstance();

    public static final SessionSingleton getSessionSingleton() {
        return sessionSingleton;
    }

}
