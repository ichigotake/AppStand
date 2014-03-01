package net.ichigotake.appstand;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

class ApplicationItemClickListener implements View.OnClickListener {

    final private String LOG_TAG = ApplicationItemClickListener.class.getSimpleName();
    final private Application mApplication;

    ApplicationItemClickListener(Application application) {
        mApplication = application;
    }

    @Override
    public void onClick(View view) {
        final Context context = view.getContext();

        try {
            final Intent intent = context.getPackageManager()
                    .getLaunchIntentForPackage(mApplication.getPackageName());
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            onException(context, e);
        }
    }

    private void onException(Context context, Exception e) {
        Toast.makeText(context, "指定したアプリが開けませんでした", Toast.LENGTH_SHORT).show();
        Log.e(LOG_TAG, "", e);
    }

}
