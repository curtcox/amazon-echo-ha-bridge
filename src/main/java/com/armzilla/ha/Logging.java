package com.armzilla.ha;

public final class Logging {
    public interface Log {
        void debug(String message);
        void info(String message);
        void error(String message, Throwable t);
    }
    private static final Log log = new Log() {
        public void debug(String message) {
            System.out.println(message);
        }

        public void info(String message) {
            System.out.println(message);
        }

        public void error(String message,Throwable t) {
            System.out.println(message);
            t.printStackTrace();
        }
    };

    public static Log forClass(Class c) {
        return log;
    }
}
