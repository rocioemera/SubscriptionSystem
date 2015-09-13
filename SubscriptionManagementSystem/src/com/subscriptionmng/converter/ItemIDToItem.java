package com.subscriptionmng.converter;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import com.subscriptionmng.data.ItemMapperImp;
import com.subscriptionmng.model.admin.ItemImp;

public class ItemIDToItem implements Converter<String, ItemImp>{
	
	private ItemMapperImp itemMapper;
	
	public ItemImp convert(String source) {
		long itemId = -1;
        try{
        	itemId = Long.parseLong(source);
        } catch (NumberFormatException e) {
            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), 
            		TypeDescriptor.valueOf(ItemImp.class), source, null);
        }

		return itemMapper.findById(Long.valueOf(itemId));
    }

	public ItemMapperImp getItemMapper() {
		return itemMapper;
	}

	public void setItemMapper(ItemMapperImp itemMapper) {
		this.itemMapper = itemMapper;
	}
}
