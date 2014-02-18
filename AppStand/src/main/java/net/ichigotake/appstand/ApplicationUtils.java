package net.ichigotake.appstand;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.List;

class ApplicationUtils {

    final private Context mContext;

    ApplicationUtils(Context context) {
        mContext = context;
    }

    boolean isInstallApp(final ApplicationInfo info) {
        return info.packageName != null &&
                (info.flags & ApplicationInfo.FLAG_SYSTEM) <= 0;
    }

    boolean activitiesExists(ApplicationInfo appInfo) {
        final Intent intent = mContext.getPackageManager()
                .getLaunchIntentForPackage(appInfo.packageName);
        return intent != null;
    }

    List<ApplicationInfo> getInstalledPackages() {
        return mContext.getPackageManager()
                .getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
    }

}
