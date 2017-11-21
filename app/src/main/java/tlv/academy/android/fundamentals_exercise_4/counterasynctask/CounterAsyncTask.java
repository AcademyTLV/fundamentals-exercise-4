package tlv.academy.android.fundamentals_exercise_4.counterasynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;

/**
 * Created by danielszasz on 19/11/2017.
 */

public class CounterAsyncTask extends AsyncTask<Integer, Integer, Integer> {

    private IAsyncTaskEvents mIAsyncTaskEvents;

    public CounterAsyncTask(IAsyncTaskEvents iAsyncTaskEvents) {
        mIAsyncTaskEvents = iAsyncTaskEvents;
    }


    @Override
    protected Integer doInBackground(Integer... integers) {
        int start, end;
        if (integers.length == 2) {
            start = integers[0];
            end = integers[1];
        } else {
            start = 0;
            end = 10;
        }

        for (int i = start; i <= end; i++) {
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
        super.onPreExecute();
        if (mIAsyncTaskEvents != null) {
            mIAsyncTaskEvents.onPreExecute();
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
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
