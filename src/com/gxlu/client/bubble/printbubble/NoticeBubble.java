package com.gxlu.client.bubble.printbubble;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

import ch.swingfx.twinkle.style.INotificationStyle;
import ch.swingfx.twinkle.style.theme.DarkDefaultNotification;

public class NoticeBubble {
	public static float b_alpha = 0.9f;
	public static int b_width = 400;
	public static String b_title = "11";
	public static String b_message = "11";
	public static int b_disp = 2000;
//	public static Positions b_pos = Positions.NORTH_EAST;
	public static ImageIcon b_icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Util.class.getResource(Util.image_t_path)));
	
	public static INotificationStyle getDefaultStyle() {
		INotificationStyle style = new DarkDefaultNotification().withAlpha(NoticeBubble.b_alpha).withWidth(NoticeBubble.b_width);
		return style;
	}
}
