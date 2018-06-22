package com.lgd.commonec.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.lgd.common_ui.recycler.MultipleRecyclerAdapter;
import com.lgd.common_ui.recycler.MultipleViewHolder;
import com.joanzapata.iconify.widget.IconTextView;
import com.lgd.commoncore.app.Latte;
import com.lgd.commoncore.delegates.LatteDelegate;
import com.lgd.commoncore.delegates.bottom.BottomItemDelegate;
import com.lgd.commoncore.net.RestClient;
import com.lgd.commoncore.net.callback.ISuccess;
import com.lgd.commoncore.util.storage.LattePreference;
import com.lgd.commonec.ec.R;
import com.lgd.commonec.ec.R2;
import com.lgd.common_ui.recycler.MultipleFields;
import com.lgd.common_ui.recycler.MultipleItemEntity;
import com.lgd.common_ui.recycler.MultipleRecyclerAdapter;
import com.lgd.common_ui.recycler.MultipleViewHolder;
import com.joanzapata.iconify.widget.IconTextView;
import com.lgd.commoncore.app.Latte;
import com.lgd.commoncore.net.RestClient;
import com.lgd.commoncore.net.callback.ISuccess;
import com.lgd.commonec.ec.R;
import com.lgd.commonec.ec.main.personal.address.AddressDelegate;
import com.lgd.commonec.ec.main.personal.list.ListAdapter;
import com.lgd.commonec.ec.main.personal.list.ListBean;
import com.lgd.commonec.ec.main.personal.list.ListItemType;
import com.lgd.commonec.ec.main.personal.order.OrderListDelegate;
import com.lgd.commonec.ec.main.personal.profile.UserProfileDelegate;
import com.lgd.commonec.ec.main.personal.settings.SettingsDelegate;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 傅令杰
 */

public class PersonalDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }

    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar() {
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    private void startOrderListByType() {
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new SettingsDelegate())
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
        mRvSettings.addOnItemTouchListener(new PersonalClickListener(this));
    }
}
