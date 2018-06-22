package com.lgd.commonec.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.lgd.common_ui.recycler.MultipleRecyclerAdapter;
import com.lgd.common_ui.recycler.MultipleViewHolder;
import com.joanzapata.iconify.widget.IconTextView;
import com.lgd.commoncore.app.Latte;
import com.lgd.commoncore.delegates.LatteDelegate;
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
import com.lgd.commonec.ec.detail.GoodsDetailDelegate;


/**
 * Created by 傅令杰
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    private IndexItemClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(LatteDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int goodsId = entity.getField(MultipleFields.ID);
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
