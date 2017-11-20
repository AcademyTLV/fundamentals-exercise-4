package tlv.academy.android.fundamentals_exercise_4.simpleasynctask;

/**
 * Created by rl98880 on 19/11/2017.
 */

public abstract class SimpleAsyncTask {
    /**
     * Runs on the UI thread before {@link #doInBackground}.
     */
    protected abstract void onPreExecute();

    /**
     * Runs on new thread after {@link #onPostExecute()} and before {@link #onPostExecute()}.
     */
    protected abstract void doInBackground();

    /**
     * Runs on the UI thread after {@link #doInBackground}
     */
    protected abstract void onPostExecute();

    /**
     * Execute Async Task
     */
    protected abstract void execute();

    protected void onProgress(){}

    protected abstract void publishProgress();

    /**
     * cancel current task
     */
    public abstract void cancel();
}
