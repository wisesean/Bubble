package com.gxlu.client.bubble.fx.demo;

import ch.swingfx.twinkle.NotificationBuilder;
import ch.swingfx.twinkle.event.NotificationEvent;
import ch.swingfx.twinkle.event.NotificationEventAdapter;
import ch.swingfx.twinkle.style.INotificationStyle;
import ch.swingfx.twinkle.style.theme.DarkDefaultNotification;
import ch.swingfx.twinkle.window.Positions;

public class QuickStart {

	public static void main(String[] args) {
		// AA the text
		System.setProperty("swing.aatext", "true");

		// First we define the style/theme of the window.
		// Note how we override the default values
//		INotificationStyle style = new DarkDefaultNotification().withWidth(400) // Optional
//				.withAlpha(0.9f) // Optional
//		;
		INotificationStyle style = new DarkDefaultNotification().withAlpha(0.9f).withWidth(400);
		// Now lets build the notification
		new NotificationBuilder()
				.withStyle(style) // Required. here we set the previously set style
				.withTitle("Set a title") // Required.
				.withMessage("Set a message") // Optional
				//.withIcon(new ImageIcon(QuickStart.class.getResource("/twinkle.png"))) // Optional. You could also use a String path
				.withDisplayTime(2000) // Optional
				.withPosition(Positions.NORTH_EAST) // Optional. Show it at the center of the screen
				.withListener(new NotificationEventAdapter() { // Optional
					public void closed(NotificationEvent event) {
						System.out.println("closed notification with UUID " + event.getId());
					}

					public void clicked(NotificationEvent event) {
						System.out.println("clicked notification with UUID " + event.getId());
					}
				})
				.showNotification(); // this returns a UUID that you can use to identify events on the listener

	}

}
