package com.lgd.commonec.ec.main.personal.address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lgd.common_ui.recycler.DataConverter;
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


import java.util.ArrayList;

/**
 * Created by 傅令杰
 */

public class AddressDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {

            final JSONObject data = array.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final String phone = data.getString("phone");
            final String address = data.getString("address");
            final boolean isDefault = data.getBoolean("default");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(AddressItemType.ITEM_ADDRESS)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.NAME, name)
                    .setField(MultipleFields.TAG, isDefault)
                    .setField(AddressItemFields.ADDRESS, address)
                    .setField(AddressItemFields.PHONE, phone)
                    .build();
            ENTITIES.add(entity);
        }

        return ENTITIES;
    }
}
