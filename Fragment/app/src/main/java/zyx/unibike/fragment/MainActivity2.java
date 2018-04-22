package zyx.unibike.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    ContentFragment contentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        add();
    }
   //第二种方式:代码方式将fragment添加到FrameLayout
    private void  add(){
       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(contentFragment==null){
            contentFragment=new ContentFragment();
        }
        transaction.add(R.id.content_layout, contentFragment);
        transaction.commit();
    }

}
