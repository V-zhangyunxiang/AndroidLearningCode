package zyx.unibike.fragment;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /**
     * fragment生命周期:
     * onAttach()
     * onCreate()
     * onCreateView()          --对应Activity中的onCreate()
     * onActivityCreated()
     *
     * 中间与Activity一样  onStart(),onResume(),onPause(),onStop()
     *
     * onDestoryView()
     * onDestory()            --对应Activity中的onDestory()
     * onDetach()
     */
    /**
       将fragment添加到activity有两种方式
       1.在activity布局文件里声明fragment
       2.通过编码将fragment添加到已存在的FrameLayout(ViewGroup)中

     fragment与Activity的交互
      1.fragment通过getActivity()函数访问Activity
      2.Activity可以通过findFragmentById()或者findFragmentByTag()获取到Fragment对象
      3.fragment与Activity共享事件
        在fragment中定义一个回调接口，Activity宿主实现它，当Activity通过接口接收回调时，可以在必要时与布局中的其它fragment共享信息
                                                      (fragment与fragment的交互，需要在其宿主Activity中把通过接口获取到的值传递到其它的fragment中去)
     */
    TitleFragment titleFragment;
    ContentFragment contentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过FragmentManager(管理器)
        //第一种:布局方式
        titleFragment= (TitleFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_title);
        contentFragment= (ContentFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_content);
    }
}
