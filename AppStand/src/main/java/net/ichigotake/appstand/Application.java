package net.ichigotake.appstand;

import android.graphics.drawable.Drawable;

interface Application {

    String getName();

    String getPackageName();

    long getLastUpdatedTime();

    Drawable getIcon();
}
