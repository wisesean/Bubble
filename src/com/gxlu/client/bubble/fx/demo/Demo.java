package com.gxlu.client.bubble.fx.demo;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.HashMap;

import ch.swingfx.twinkle.style.AbstractNotificationStyle;
import ch.swingfx.twinkle.style.INotificationStyle;
import ch.swingfx.twinkle.style.theme.DarkDefaultNotification;
import ch.swingfx.twinkle.window.DefaultNotificationWindow;

public class Demo {
	/**
	 * @wbp.parser.entryPoint
	 */
	public Demo() {
	      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	      GraphicsDevice[] gs = ge.getScreenDevices();
	      for (int j = 0; j < gs.length; j++) { 
	          GraphicsDevice gd = gs[j];
	          GraphicsConfiguration[] gc =gd.getConfigurations();
	          INotificationStyle style = new DarkDefaultNotification().withAlpha(0.9f).withWidth(400);
	          DefaultNotificationWindow w = new DefaultNotificationWindow(null, "11", "11", style, gc[0]);
	          w.setSize(400, 600);
	          w.setVisible(true);
	      } 
		
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		new Demo();
	}
}
