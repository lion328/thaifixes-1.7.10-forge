package com.lion328.thaifixes;

import net.minecraft.util.ResourceLocation;

public class ThaiFixesUtils {
	
	public static boolean isThaiChar(char par1)
	{
		return par1 >= 3585 && par1 <= 3675;
	}
	
	public static boolean isSpecialThaiChar(char par1)
	{
		return isUpperThaiChar(par1) || isLowerThaiChar(par1);
	}
	
	public static boolean isUpperThaiChar(char par1)
	{
		return "ัิีึื็่้๊๋์ํ๎".indexOf(par1) != -1;
	}
	
	public static boolean isLowerThaiChar(char par1)
	{
		return "ฺุู".indexOf(par1) != -1;
	}
	
	public static char covertKeycharToUnicode(char par1)
	{
		return (char)((int)par1 + 3424);
	}
	
	public static char covertToThai(char par1)
	{
		return isThaiChar(covertKeycharToUnicode(par1)) ? covertKeycharToUnicode(par1) : par1;
	}
}