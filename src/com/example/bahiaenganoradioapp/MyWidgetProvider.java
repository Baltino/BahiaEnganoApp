package com.example.bahiaenganoradioapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider
{
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
         final int N = appWidgetIds.length;
 
             // Perform this loop procedure for each App Widget that belongs to this provider
         for (int i=0; i<N; i++) {
             int appWidgetId = appWidgetIds[i];
 
             // Create an Intent to launch Activity
             Intent intent = new Intent(context, MainActivity.class);
             PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
 
          // use this to start and trigger a service
             Intent serviceIntent= new Intent(context, MyRadioService.class);
             PendingIntent pendingServiceIntent = PendingIntent.getService(context, 0, serviceIntent, 0);
 
             
             // Get the layout for the App Widget and attach an on-click listener
             // to the button
             RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                 
             views.setOnClickPendingIntent(R.id.imageView1, pendingIntent);
             views.setOnClickPendingIntent(R.id.play_button, pendingServiceIntent);
             
             // Tell the AppWidgetManager to perform an update on the current app widget
             appWidgetManager.updateAppWidget(appWidgetId, views);
         }
     }
}