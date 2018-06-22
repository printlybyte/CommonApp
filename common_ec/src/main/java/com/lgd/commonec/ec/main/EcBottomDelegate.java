package com.lgd.commonec.ec.main;

import android.graphics.Color;

import com.lgd.common_ui.recycler.MultipleRecyclerAdapter;
import com.lgd.common_ui.recycler.MultipleViewHolder;
import com.joanzapata.iconify.widget.IconTextView;
import com.lgd.commoncore.app.Latte;
import com.lgd.commoncore.delegates.LatteDelegate;
import com.lgd.commoncore.delegates.bottom.BaseBottomDelegate;
import com.lgd.commoncore.delegates.bottom.BottomItemDelegate;
import com.lgd.commoncore.delegates.bottom.BottomTabBean;
import com.lgd.commoncore.delegates.bottom.ItemBuilder;
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
import com.lgd.commonec.ec.main.cart.ShopCartDelegate;
import com.lgd.commonec.ec.main.discover.DiscoverDelegate;
import com.lgd.commonec.ec.main.index.IndexDelegate;
import com.lgd.commonec.ec.main.personal.PersonalDelegate;
import com.lgd.commonec.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by 傅令杰
 */

public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
