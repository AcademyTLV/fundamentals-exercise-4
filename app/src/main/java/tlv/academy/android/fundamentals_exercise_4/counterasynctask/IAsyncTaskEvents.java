package tlv.academy.android.fundamentals_exercise_4.counterasynctask;

/**
 * Created by danielszasz on 19/11/2017.
 */

public interface IAsyncTaskEvents {
    void onPreExecute();

    void onPostExecute();

    void onProgressUpdate(Integer integer);

    void onCancel();
}
