package tlv.academy.android.fundamentals_exercise_4;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends Activity implements IAsyncTaskEvents{

    private Button mBtnCreate;
    private Button mBtnStart;
    private Button mBtnCancel;
    private TextView mTxtValue;

    private ColorChangeAsyncTask mColorChangeAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_async_task);

        mBtnCreate = findViewById(R.id.btnAsyncCreate);
        mBtnStart = findViewById(R.id.btnAsyncStart);
        mBtnCancel = findViewById(R.id.btnAsyncCancel);
        mTxtValue = findViewById(R.id.txtAsyncValue);

        mBtnCreate.setOnClickListener( OnClickCreate);
        mBtnStart.setOnClickListener( OnClickStart);
        mBtnCancel.setOnClickListener( OnClickCancel);
    }

    private View.OnClickListener OnClickCreate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doAsyncTaskCreate();
        }
    };

    private View.OnClickListener OnClickStart = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doAsyncTaskStart();
        }
    };

    private View.OnClickListener OnClickCancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doAsyncTaskCancel();
        }
    };

    @Override
    public void onPreExecute() {
        Toast.makeText( this, getString(R.string.msg_preexecute), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostExecute() {
        Toast.makeText( this, getString(R.string.msg_postexecute), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProgressUpdate(Integer aInteger) {
        mTxtValue.setText( String.valueOf( aInteger));

    }


    private void doAsyncTaskCreate(){
        mColorChangeAsyncTask = new ColorChangeAsyncTask( this);
    }

    private void doAsyncTaskStart(){
        Integer integerStart;
        Integer integerEnd;

        integerStart = 1;
        integerEnd = 100;

        mColorChangeAsyncTask.execute( integerStart, integerEnd);
    }

    private void doAsyncTaskCancel(){
        mColorChangeAsyncTask.cancel( true);
    }
}
