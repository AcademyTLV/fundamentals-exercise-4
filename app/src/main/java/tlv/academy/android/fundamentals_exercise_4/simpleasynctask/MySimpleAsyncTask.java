package tlv.academy.android.fundamentals_exercise_4.simpleasynctask;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by rl98880 on 19/11/2017.
 */

public abstract class MySimpleAsyncTask<Param> extends SimpleAsyncTask<Param> {
    private Thread mBackgroundThread;

    /**
     * Runs on the UI thread before {@link #doInBackground}.
     */
    protected abstract void onPreExecute();

    /**
     * Runs on new thread after {@link #onPostExecute()} and before {@link #onPostExecute()}.
     */
    protected abstract Param doInBackground();

    /**
     * Runs on the UI thread after {@link #doInBackground}
     */
    protected abstract void onPostExecute();

    @Override
    public void execute() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onPreExecute();
                mBackgroundThread = new Thread("Handler_executor_thread") {
                    @Override
                    public void run() {
                        doInBackground();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                onPostExecute();
                            }
                        });
                    }
                };
                mBackgroundThread.start();
            }
        });
    }

    private void runOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    @Override
    public void cancel() {
        mCancelled = true;
        if (mBackgroundThread != null) {
            mBackgroundThread.interrupt();
        }
    }

    @Override
    protected void publishProgress(final Param... values) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate(values);
            }
        });
    }

}