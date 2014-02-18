package net.ichigotake.appstand;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class PackageAdapter  extends ArrayAdapter<ApplicationInfo> {

    PackageAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);

        final ApplicationInfo info = getItem(position);
        final TextView appView  = (TextView)convertView.findViewById(android.R.id.text1);
        appView.setText(info.packageName.replace(BuildConfig.BASE_PACKAGE_NAME, ""));

        convertView.setOnClickListener(new ApplicationItemClickListener(info));

        return convertView;
    }
}
