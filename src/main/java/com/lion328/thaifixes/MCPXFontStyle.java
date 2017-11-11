package com.lion328.thaifixes;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class MCPXFontStyle {
	
	public static final MCPXFontStyle instance = new MCPXFontStyle(Minecraft.getMinecraft().fontRenderer);
	
	public static final int THAI_CHAR_START = 3584, THAI_CHAR_END = 3675, THAI_CHAR_SIZE = THAI_CHAR_END - THAI_CHAR_START;
	
	private final ResourceLocation textureLocation;
	private final FontRenderer renderer;
	private int[] charWidth = new int[THAI_CHAR_SIZE];
	
	public MCPXFontStyle(FontRenderer renderer) {
		this.renderer = renderer;
		textureLocation = new ResourceLocation("thaifixes", "textures/font/thai.png");
		loadCharWidth();
	}
	
	private void loadCharWidth() {
        BufferedImage bufferedimage;

        try
        {
            bufferedimage = ImageIO.read(this.getClass().getResourceAsStream("/assets/thaifixes/textures/font/thai.png"));
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException(ioexception);
        }

        int w = bufferedimage.getWidth(); //i
        int h = bufferedimage.getHeight(); //j
        int[] tex = new int[w * h];
        bufferedimage.getRGB(0, 0, w, h, tex, 0, w);
        int k = w / 16;
        int l = w / 16;
        byte b0 = 1;
        float f = 8.0F / (float)l;
        int i1 = 0;

        while (i1 < THAI_CHAR_SIZE)
        {
            int col = i1 % 16;
            int row = i1 / 16;
            int l1 = l - 1;

            while (true)
            {
                if (l1 >= 0)
                {
                    int i2 = col * l + l1;
                    boolean flag = true;

                    for (int j2 = 0; j2 < k && flag; ++j2)
                    {
                        int k2 = (row * l + j2) * w;

                        if ((tex[i2 + k2] >> 24 & 0xFF) != 0)
                        {
                            flag = false;
                        }
                    }

                    if (flag)
                    {
                        --l1;
                        continue;
                    }
                }

                ++l1;
                this.charWidth[i1] = (int)(0.5D + (double)((float)l1 * f)) + b0;
                ++i1;
                break;
            }
        }
	}
	
	public int getCharWidth(char c) {
		return c >= THAI_CHAR_START ? charWidth[c - THAI_CHAR_START] : renderer.getCharWidth(c);
	}
	
	public float render(TextureManager renderEngine, float posX, float posY, char c, boolean italic) {
		c -= THAI_CHAR_START;
		float f = (float)(c % 16 * 8);
        float f1 = (float)(c / 16 * 8);
        float f2 = italic ? 1.0F : 0.0F;
        renderEngine.bindTexture(textureLocation);
        float f3 = (float)this.charWidth[c] - 0.01F;
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glTexCoord2f(f / 128.0F, f1 / 128.0F);
        GL11.glVertex3f(posX + f2, posY, 0.0F);
        GL11.glTexCoord2f(f / 128.0F, (f1 + 7.99F) / 128.0F);
        GL11.glVertex3f(posX - f2, posY + 7.99F, 0.0F);
        GL11.glTexCoord2f((f + f3 - 1.0F) / 128.0F, f1 / 128.0F);
        GL11.glVertex3f(posX + f3 - 1.0F + f2, posY, 0.0F);
        GL11.glTexCoord2f((f + f3 - 1.0F) / 128.0F, (f1 + 7.99F) / 128.0F);
        GL11.glVertex3f(posX + f3 - 1.0F - f2, posY + 7.99F, 0.0F);
        GL11.glEnd();
        return (float)this.charWidth[c];
	}
}
