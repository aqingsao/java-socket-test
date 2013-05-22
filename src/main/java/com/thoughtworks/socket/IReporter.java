package com.thoughtworks.socket;

public abstract class IReporter {

    public static final IReporter DUMMY_REPORTER = new IReporter() {

        @Override
        public void reportStatus(int successCount) {
        }

        @Override
        public void reportStarted() {
        }

        @Override
        public void reportEnded(int successCount) {
        }
    };

    public abstract void reportStatus(int successCount);

    public abstract void reportStarted();

    public abstract void reportEnded(int successCount);

    public static IReporter getDummyReporter() {
        return DUMMY_REPORTER;
    }
}