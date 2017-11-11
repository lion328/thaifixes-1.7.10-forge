package com.lion328.thaifixes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.minecraft.client.Minecraft;

public class ThaiFixesConfiguration {
	
	private static Properties prop = new Properties();
	private static FONT_STYLE fontstyle;
	
	public static boolean loadConfig() {
		try {
			File cfg = new File(Minecraft.getMinecraft().mcDataDir.getPath() + "/config/ThaiFixes.cfg");
			if(!cfg.exists()) {
				InputStream in = ThaiFixesConfiguration.class.getResourceAsStream("/assets/thaifixes/config/ThaiFixes.cfg");
				FileOutputStream out = new FileOutputStream(cfg);
				byte[] buffer = new byte[1024];
				int readBytes;
				while((readBytes = in.read(buffer)) != -1)
					out.write(buffer, 0, readBytes);
				out.close();
				in.close();
			}
			FileInputStream in = new FileInputStream(cfg);
			prop.load(in);
			in.close();
			fontstyle = prop.containsKey("font.style") ?
					(prop.getProperty("font.style").equalsIgnoreCase("default") ? FONT_STYLE.DEFAULT : FONT_STYLE.MCPX)
					: FONT_STYLE.DEFAULT;
		} catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static FONT_STYLE getFontStyle() {
		return fontstyle;
	}
	
	static {
		loadConfig();
	}
	
	public static enum FONT_STYLE {DEFAULT, MCPX};
}
