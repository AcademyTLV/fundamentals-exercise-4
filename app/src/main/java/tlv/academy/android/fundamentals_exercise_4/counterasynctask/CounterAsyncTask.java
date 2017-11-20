package tlv.academy.android.fundamentals_exercise_4.counterasynctask;

import android.os.AsyncTask;
import android.os.SystemClock;

import tlv.academy.android.fundamentals_exercise_4.counterasynctask.IAsyncTaskEvents;

/**
 * Created by danielszasz on 19/11/2017.
 */

public class CounterAsyncTask extends AsyncTask<Integer, Integer, Integer> {

    private IAsyncTaskEvents mIAsyncTaskEvents;

    public CounterAsyncTask(IAsyncTaskEvents aIAsyncTaskEvents) {
        mIAsyncTaskEvents = aIAsyncTaskEvents;
    }

    @Override
    protected Integer doInBackground(Integer... aIntegers) {

        int iStart;
        int iEnd;
        if (aIntegers.length == 2) {
            iStart = aIntegers[0];
            iEnd = aIntegers[1];
        } else {
            iStart = 0;
            iEnd = 100;
        }

        for (int iStep = iStart; iStep < iEnd; iStep++) {
            SystemClock.sleep(250);
            publishProgress(iStep);
        }

        return iEnd;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mIAsyncTaskEvents != null) {
            mIAsyncTaskEvents.onPreExecute();
        }
    }

    @Override
    protected void onPostExecute(Integer aInteger) {
        super.onPostExecute(aInteger);
        if (mIAsyncTaskEvents != null) {
            mIAsyncTaskEvents.onPostExecute();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        if (mIAsyncTaskEvents != null) {
            mIAsyncTaskEvents.onProgressUpdate(values[0]);
        }
    }

    @Override
    protected void onCancelled(Integer aInteger) {
        super.onCancelled(aInteger);
        if (mIAsyncTaskEvents != null) {
            mIAsyncTaskEvents.onCancel();
        }

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (mIAsyncTaskEvents != null) {
            mIAsyncTaskEvents.onCancel();
        }
    }
}
