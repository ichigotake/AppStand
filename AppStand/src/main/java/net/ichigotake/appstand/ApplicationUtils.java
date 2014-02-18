package net.ichigotake.appstand;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

class ApplicationUtils {

    final private Context mContext;

    ApplicationUtils(Context context) {
        mContext = context;
    }

    List<Application> getInstalledPackages() {
        final List<Application> apps = new ArrayList<Application>();
        final PackageManager packageManager = mContext.getPackageManager();
        final List<ApplicationInfo> infoList = packageManager
                .getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);

        for (ApplicationInfo info : infoList) {
            if (isInstallApp(info) && activitiesExists(info)) {
                final ApplicationBuilder builder = new ApplicationBuilder()
                        .setPackageName(info.packageName);

                try {
                    final PackageInfo packageInfo = packageManager
                            .getPackageInfo(info.packageName, PackageManager.GET_ACTIVITIES);
                    final Drawable icon = packageManager.getApplicationIcon(info);
                    final String appName = info.loadLabel(packageManager).toString();

                    builder.setName(appName);
                    builder.setIcon(icon);
                    if (Build.VERSION.SDK_INT >= 9) {
                        builder.setLastUpdated(packageInfo.lastUpdateTime);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                apps.add(builder.build());
            }
        }

        return apps;
    }

    private boolean activitiesExists(ApplicationInfo appInfo) {
        final Intent intent = mContext.getPackageManager()
                .getLaunchIntentForPackage(appInfo.packageName);
        return intent != null;
    }

    private boolean isInstallApp(final ApplicationInfo info) {
        return info.packageName != null &&
                (info.flags & ApplicationInfo.FLAG_SYSTEM) <= 0;
    }

}
