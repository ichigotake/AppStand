package net.ichigotake.appstand;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

class PackageAdapter extends ArrayAdapter<Application> {

    private LayoutInflater mInflater;

    PackageAdapter(Context context) {
        super(context, R.layout.application);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.application, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final Application app = getItem(position);
        viewHolder.name.setText(app.getName());
        viewHolder.packageName.setText(app.getPackageName().replace(BuildConfig.BASE_PACKAGE_NAME, ""));
        viewHolder.icon.setImageDrawable(app.getIcon());
        if (app.getLastUpdatedTime() > 0) {
            viewHolder.lastUpdated.setText(getDateFormatter(app.getLastUpdatedTime()));
        } else {
            viewHolder.lastUpdated.setText("");
        }

        convertView.setOnClickListener(new ApplicationItemClickListener(app));

        return convertView;
    }

    private LayoutInflater getLayoutInflater() {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(getContext());
        }
        return mInflater;
    }

    private String getDateFormatter(long lastUpdateTime) {
        final Calendar calendar = Calendar.getInstance(Locale.JAPAN);
        calendar.setTimeInMillis(lastUpdateTime);
        return DateFormat.format("yyyy/MM/dd, E, kk:mm:ss", calendar).toString();
    }

    private static class ViewHolder {
        ImageView icon;
        TextView name;
        TextView packageName;
        TextView lastUpdated;

        ViewHolder(View view) {
            icon = (ImageView)view.findViewById(R.id.applicationIcon);
            name = (TextView)view.findViewById(R.id.applicationName);
            packageName = (TextView)view.findViewById(R.id.applicationPackageName);
            lastUpdated = (TextView)view.findViewById(R.id.applicationLastUpdated);
        }
    }

}
