package com.lion328.thaifixes;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;

import com.lion328.thaifixes.ThaiFixesClassTransformer;
import com.lion328.thaifixes.ThaiFixesDummyContainer;

import java.io.File;
import java.util.Map;

import net.minecraft.util.ResourceLocation;

@IFMLLoadingPlugin.MCVersion(value = "1.7.10")
public class ThaiFixesCore implements IFMLLoadingPlugin {

   public static File location;
   public static final ResourceLocation font = new ResourceLocation("thaifixes", "textures/font/unicode_page_0e.png");
   //public static final boolean DEBUG = true;

   public String[] getASMTransformerClass() {
      return new String[]{ThaiFixesClassTransformer.class.getName()};
   }

   public void injectData(Map data) {
      location = (File)data.get("coremodLocation");
   }

   public String[] getLibraryRequestClass() {
      return null;
   }

   public String getModContainerClass() {
      return ThaiFixesDummyContainer.class.getName();
   }

   public String getSetupClass() {
      return null;
   }

   public String getAccessTransformerClass() {
      return null;
   }
}
