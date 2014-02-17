package net.ichigotake.appstand;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * アプリ一覧
 */
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

        final PackageAdapter adapter = new PackageAdapter(getActivity());
        final List<ApplicationInfo> installedAppList = getInstalledPackages();
        for (ApplicationInfo appInfo : installedAppList) {
            Log.d("StandFragment", "p¥name: " + appInfo.packageName);
            if ( isInstallApp(appInfo)
                    && appInfo.packageName.startsWith(BuildConfig.BASE_PACKAGE_NAME)) {
                adapter.add(appInfo);
            }
        }

        ((ListView)getView().findViewById(R.id.packages)).setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ((TextView)getView().findViewById(R.id.basePackageName)).setText(BuildConfig.BASE_PACKAGE_NAME);
    }

    private boolean isInstallApp(final ApplicationInfo info) {
        return info.packageName != null &&
                (info.flags & ApplicationInfo.FLAG_SYSTEM) <= 0;
    }

    private List<ApplicationInfo> getInstalledPackages() {
        return getActivity().getPackageManager()
                .getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
    }

    private static class PackageAdapter extends ArrayAdapter<ApplicationInfo> {

        public PackageAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_1);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.getView(position, convertView, parent);

            final ApplicationInfo info = getItem(position);
            final TextView appView  = (TextView)convertView.findViewById(android.R.id.text1);
            appView.setText(info.packageName.replace(BuildConfig.BASE_PACKAGE_NAME, ""));

            convertView.setOnClickListener(new AppItemClickListener(info));

            return convertView;
        }
    }

    private static class AppItemClickListener implements View.OnClickListener {

        final private String LOG_TAG = AppItemClickListener.class.getSimpleName();
        final private ApplicationInfo mAppInfo;

        AppItemClickListener(ApplicationInfo appInfo) {
            mAppInfo = appInfo;
        }

        @Override
        public void onClick(View view) {
            final Context context = view.getContext();
            try {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage(mAppInfo.packageName);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } catch (Exception e) {
                Log.e(LOG_TAG, "", e);
                Toast.makeText(context, "指定したアプリが開けませんでした", Toast.LENGTH_SHORT);
            }
        }
    }

}
