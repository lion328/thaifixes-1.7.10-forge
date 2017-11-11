package com.lion328.thaifixes;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.util.Arrays;

public class ThaiFixesDummyContainer extends DummyModContainer {

   private int majorVersion = 2;
   private int minorVersion = 0;
   private int revisionVersion = 0;


   public ThaiFixesDummyContainer() {
      super(new ModMetadata());
      ModMetadata meta = this.getMetadata();
      meta.modId = "ThaiFixes";
      meta.name = "ThaiFixes";
      meta.version = String.format("%d.%d.%d", new Object[]{Integer.valueOf(this.majorVersion), Integer.valueOf(this.minorVersion), Integer.valueOf(this.revisionVersion)});
      meta.credits = "iSuzutsuki for class transformer codes.";
      meta.authorList = Arrays.asList(new String[]{"lion328"});
      meta.description = "";
      meta.url = "http://thaifixes.lion328.com";
      meta.updateUrl = "";
      meta.screenshots = new String[0];
      meta.logoFile = "";
   }

   public boolean registerBus(EventBus bus, LoadController controller) {
      bus.register(this);
      return true;
   }
   
	@Subscribe
	public void modConstruction(FMLConstructionEvent evt){}
	
	@Subscribe
	public void init(FMLInitializationEvent evt) {}
	
	@Subscribe
	public void preInit(FMLPreInitializationEvent evt) {}
	
	@Subscribe
	public void postInit(FMLPostInitializationEvent evt) {}
}
