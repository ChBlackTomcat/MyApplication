package activity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.first.myapplication.R;

import java.util.ArrayList;

import base.BaseFragment;
import bean.UserMessage;
import fragment.HomeFragment;
import fragment.ShopCartFragment;
import fragment.TypeFragment;
import fragment.UserFragment;
import utilse.Utils;

/**
 * Created by 小黑 on 2019/8/5.
 */

public class MainActivity extends FragmentActivity {
    private RadioGroup rg;
    private UserMessage userMessage;
    private FrameLayout frameLayout;
    private ArrayList<BaseFragment> fragments;
    private BaseFragment mContext;
    private int position = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userMessage = (UserMessage) getIntent().getSerializableExtra("userMessage");
        Utils.insert(this,userMessage);
        rg = (RadioGroup) findViewById(R.id.rg_main);
        frameLayout = (FrameLayout) findViewById(R.id.main_frameLayout);
        initFragment();
        initListener();

    }

    private void initListener() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_cart:
                        position = 2;
                        break;
                    case R.id.rb_user:
                        position = 3;
                        break;
                }
                BaseFragment nextFragment = fragments.get(position);
                switchFragment(mContext,nextFragment);
            }
        });
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new ShopCartFragment());
        fragments.add(new UserFragment());
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (mContext != nextFragment) {
            mContext = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.delet(this,userMessage);
    }
}
