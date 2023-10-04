package com.example.lich.service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.tatv.baseapp.service.BaseService;
public class TimeService extends BaseService {
    @Override
    protected void onReceiverBroadcast(Context context, Intent intent) {

    }

    @Override
    protected String[] getIntentFilters() {
        return new String[0];
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
