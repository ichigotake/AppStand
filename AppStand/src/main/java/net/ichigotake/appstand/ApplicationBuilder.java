package net.ichigotake.appstand;

class ApplicationBuilder {

    private String mName;
    private String mPackageName;
    private long mLastUpdatedTime;

    Application build() {
        return new ApplicationImpl();
    }

    private class ApplicationImpl implements Application {

        private ApplicationImpl() {}

        @Override
        public String getName() {
            return mName;
        }

        @Override
        public String getPackageName() {
            return mPackageName;
        }

        @Override
        public long getLastUpdatedTime() {
            return mLastUpdatedTime;
        }

    }

    ApplicationBuilder setName(String name) {
        mName = name;
        return this;
    }

    ApplicationBuilder setPackageName(String packageName) {
        mPackageName = packageName;
        return this;
    }

    ApplicationBuilder setLastUpdated(long lastUpdated) {
        mLastUpdatedTime = lastUpdated;
        return this;
    }

}
