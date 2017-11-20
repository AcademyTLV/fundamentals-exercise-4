package tlv.academy.android.fundamentals_exercise_4;

import android.os.AsyncTask;
import android.os.SystemClock;

/**
 * Created by danielszasz on 19/11/2017.
 */

public class ColorChangeAsyncTask extends AsyncTask<Integer, Integer, Integer>{

    private IAsyncTaskEvents mIAsyncTaskEvents;

    public ColorChangeAsyncTask(IAsyncTaskEvents aIAsyncTaskEvents) {
        mIAsyncTaskEvents = aIAsyncTaskEvents;
    }

    @Override
    protected Integer doInBackground(Integer... aIntegers) {
        int startColor = aIntegers[0];
        int endColor = aIntegers[1];
        while (startColor < endColor) {
            SystemClock.sleep(200);
            int nextColor = aIntegers[0] += 10;
            publishProgress(nextColor);
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mIAsyncTaskEvents.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer aInteger) {
        super.onPostExecute(aInteger);
        mIAsyncTaskEvents.onProgressUpdate(aInteger);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mIAsyncTaskEvents.onProgressUpdate(values[0]);
    }

    @Override
    protected void onCancelled(Integer aInteger) {
        super.onCancelled(aInteger);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
