package net.ichigotake.appstand;

import android.graphics.drawable.Drawable;

class ApplicationBuilder {

    private String mName;
    private String mPackageName;
    private long mLastUpdatedTime;
    private Drawable mIcon;

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

        @Override
        public Drawable getIcon() {
            return mIcon;
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

    ApplicationBuilder setIcon(Drawable icon) {
        mIcon = icon;
        return this;
    }
}
