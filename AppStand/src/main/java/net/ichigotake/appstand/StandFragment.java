package net.ichigotake.appstand;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
        for (Application app : utils.getInstalledPackages()) {
            if (app.getPackageName().startsWith(BuildConfig.BASE_PACKAGE_NAME)) {
                adapter.add(app);
            }
        }

        ((ListView)getView().findViewById(R.id.packages)).setAdapter(adapter);
        adapter.notifyDataSetChanged();

        getSupportActionBar().setTitle(BuildConfig.BASE_PACKAGE_NAME);
    }

    private ActionBar getSupportActionBar() {
        return ((ActionBarActivity)getActivity()).getSupportActionBar();
    }
}
