package cn.wildfire.chat.kit.group.manage;

import cn.wildfire.chat.kit.WfcBaseActivity;
import com.hskj.example.R2; import com.hskj.example.R;
import cn.wildfirechat.model.GroupInfo;

public class GroupManagerListActivity extends WfcBaseActivity {

    @Override
    protected void afterViews() {
        GroupInfo groupInfo = getIntent().getParcelableExtra("groupInfo");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFrameLayout, GroupManagerListFragment.newInstance(groupInfo))
                .commit();
    }

    @Override
    protected int contentLayout() {
        return R.layout.fragment_container_activity;
    }
}
