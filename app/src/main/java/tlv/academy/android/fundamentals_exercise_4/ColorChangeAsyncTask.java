package tlv.academy.android.fundamentals_exercise_4;

import android.os.AsyncTask;

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
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer aInteger) {
        super.onPostExecute(aInteger);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
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
