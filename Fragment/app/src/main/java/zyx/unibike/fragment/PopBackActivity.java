package zyx.unibike.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

public class PopBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_back);
    }

    public void oneClick(View v) {
        PopBackFragment p1 =PopBackFragment.getInstance("11");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, p1);
        //把当前Fragment添加到Activity栈中
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void twoClick(View v) {
        PopBackFragment p1 =PopBackFragment.getInstance("22");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, p1);
        //把当前Fragment添加到Activity栈中
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(getSupportFragmentManager().getBackStackEntryCount()==0){
                     finish();
            }else{
                getSupportFragmentManager().popBackStack();//出栈操作
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
