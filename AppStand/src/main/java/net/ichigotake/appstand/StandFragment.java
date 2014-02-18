package net.ichigotake.appstand;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class StandFragment extends Fragment {

    public static StandFragment newInstance() {
        return new StandFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        final ApplicationUtils utils = new ApplicationUtils(getActivity());
        final PackageAdapter adapter = new PackageAdapter(getActivity());
        final List<ApplicationInfo> installedAppList = utils.getInstalledPackages();
        for (ApplicationInfo appInfo : installedAppList) {
            if ( utils.isInstallApp(appInfo)
                    && utils.activitiesExists(appInfo)
                    && appInfo.packageName.startsWith(BuildConfig.BASE_PACKAGE_NAME)) {
                adapter.add(appInfo);
            }
        }

        ((ListView)getView().findViewById(R.id.packages)).setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ((TextView)getView().findViewById(R.id.basePackageName)).setText(BuildConfig.BASE_PACKAGE_NAME);
    }

}
