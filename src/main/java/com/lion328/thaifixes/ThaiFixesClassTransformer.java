package com.lion328.thaifixes;

import com.lion328.thaifixes.ThaiFixesCore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.minecraft.launchwrapper.IClassTransformer;

public class ThaiFixesClassTransformer implements IClassTransformer {

	public byte[] transform(String arg0, String arg1, byte[] arg2) {
		if(arg0.equals("bbu") || arg0.equals("net.minecraft.client.gui.FontRenderer")) {
			/*if(ThaiFixesCore.DEBUG && arg0.equals("net.minecraft.client.gui.FontRenderer")) {
				InputStream in = this.getClass().getResourceAsStream("FontRenderer.class");
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				int readBytes;
				byte[] buffer = new byte[1024];
				try {
					while((readBytes = in.read(buffer)) != -1) {
						out.write(buffer, 0, readBytes);
					}
					byte[] patched = out.toByteArray();
					arg2 = patched;
					return arg2;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}*/
			arg2 = this.patchClass(arg0, arg2, arg0, ThaiFixesCore.location);
		}
		return arg2;
	}

	public byte[] patchClass(String name, byte[] bytes, String ObfName, File location) {
		try {
			ZipFile zip = new ZipFile(location);
			ZipEntry entry = zip.getEntry(name.replace('.', '/') + ".class");

			if (entry == null) {
				System.out.println("[ThaiFixes] " + name + " not found in " + location.getName());
			} else {
				InputStream zin = zip.getInputStream(entry);
				int size = (int) entry.getSize();
				byte[] newbytes = new byte[size];
				int pos = 0;
				while (pos < size) {
					int len = zin.read(newbytes, pos, size - pos);
					if (len == 0)
						throw new IOException();
					pos += len;
				}
				if (!newbytes.equals(bytes))
					bytes = newbytes;
				zin.close();
				System.out.println("[ThaiFixes]: " + "Class " + name + " patched!");
			}
			zip.close();
		} catch (Exception e) {
			throw new RuntimeException("Error overriding " + name + " from " + location.getName(), e);
		}

		return bytes;
	}
}
