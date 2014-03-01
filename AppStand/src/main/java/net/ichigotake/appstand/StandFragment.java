package net.ichigotake.appstand;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SpinnerAdapter;

public class StandFragment extends Fragment {

    public static StandFragment newInstance() {
        return new StandFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SpinnerAdapter adapter = new ApplicationFilterAdapter(getActivity());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(adapter, new OnFilterSelectedListener());
        actionBar.setTitle("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        PackageAdapter adapter = new PackageAdapter(getActivity());
        getListView(view).setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh(ApplicationFilter.USUAL);
    }

    private void refresh(ApplicationFilter filterName) {
        Filter filter = filterName.createFilter();
        ApplicationUtils utils = new ApplicationUtils(getActivity());
        PackageAdapter adapter = getAdapter(getView());
        adapter.clear();
        for (Application app : utils.getInstalledPackages()) {
            if (filter.apply(app.getPackageName())) {
                adapter.add(app);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private ListView getListView(View container) {
        return (ListView)container.findViewById(R.id.packages);
    }

    private PackageAdapter getAdapter(View container) {
        return (PackageAdapter)(getListView(container)).getAdapter();
    }

    private ActionBar getSupportActionBar() {
        return ((ActionBarActivity)getActivity()).getSupportActionBar();
    }

    private class OnFilterSelectedListener implements ActionBar.OnNavigationListener {

        @Override
        public boolean onNavigationItemSelected(int position, long id) {
            refresh(ApplicationFilter.values()[position]);
            return true;
        }
    }
}
