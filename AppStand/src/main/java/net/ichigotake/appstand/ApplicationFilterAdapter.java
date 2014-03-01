package net.ichigotake.appstand;

import android.content.Context;
import android.widget.ArrayAdapter;

class ApplicationFilterAdapter extends ArrayAdapter<CharSequence> {

    ApplicationFilterAdapter(Context context) {
        super(context, android.R.layout.simple_spinner_item, getMenu(context));
    }

    private static CharSequence[] getMenu(Context context) {
        return context.getResources().getTextArray(R.array.applicationFilter);
    }
}
