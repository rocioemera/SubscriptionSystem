package com.subscriptionmng.converter;


//import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import com.subscriptionmng.data.ItemMapperImp;

public class CustomFormBinder extends CustomCollectionEditor
{
   private final ItemMapperImp dao;

   //private static final Logger LOG = LoggerFactory.getLogger(CustomFormBinder.class);

   public CustomFormBinder(ItemMapperImp daoIn, final Class collectionType)
   {
      super(collectionType, true);
      dao = daoIn;
   }

   @Override
   protected Object convertElement(final Object element)
   {
      try
      {
         // forms should return the id as the itemValue
    	 System.out.println(element.toString());
         return dao.findById((Long.valueOf(element.toString())));
      }
      catch (NumberFormatException e)
      {
         //LOG.warn("Unable to convert " + element + " to an integer");
         return null;
      }
   }

}
