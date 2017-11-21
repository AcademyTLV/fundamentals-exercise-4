package tlv.academy.android.fundamentals_exercise_4.activities;

import android.os.SystemClock;

import tlv.academy.android.fundamentals_exercise_4.counterasynctask.IAsyncTaskEvents;
import tlv.academy.android.fundamentals_exercise_4.simpleasynctask.MySimpleAsyncTask;

/**
 * Created by britt on 11/20/17.
 */

public class MyCounterAsynTaskImpl extends MySimpleAsyncTask<Integer> {
    private IAsyncTaskEvents mIAsyncTaskEvents;

    public MyCounterAsynTaskImpl(IAsyncTaskEvents iAsyncTaskEvents) {
        this.mIAsyncTaskEvents = iAsyncTaskEvents;
    }

    @Override
    protected Integer doInBackground() {
        int end = 10;
        for (int i = 0; i <= end; i++) {
            if(isCancelled()) {
                return i;
            }
            publishProgress(i);
            SystemClock.sleep(500);
        }

        return end;
    }

    @Override
    protected void onPreExecute() {
        if (mIAsyncTaskEvents != null) {
            mIAsyncTaskEvents.onPreExecute();
        }
    }

    @Override
    protected void onPostExecute() {
        if (mIAsyncTaskEvents != null) {
            mIAsyncTaskEvents.onPostExecute();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (mIAsyncTaskEvents != null) {
            mIAsyncTaskEvents.onProgressUpdate(values[0]);
        }
    }


}
