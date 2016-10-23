package com.robur58.web.rss;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.digester.rss.RSSDigester;

import de.nava.informa.core.ChannelFormat;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.parsers.FeedParser;

public class RSSFeedReader {

  public static List read(String feedAddress) {
    URL feed = null;

    try {
      feed = new URL(feedAddress);
    } catch (MalformedURLException mue) {
      mue.printStackTrace();
    }
    
    return read(feed);
  }

  public static List read(URL feed) {
    List lijst = new ArrayList();

    try {
      // http://87.208.206.190/rss/homeplate.xml
      // http://nu.nl/deeplink_rss2/index.jsp?r=Sport
      // http://news.google.com/news?q=baseball&output=rss
      // http://news.google.com/news?hl=nl&client=firefox-a&rls=org.mozilla%3Anl%3Aofficial&hs=v5Iq=baseball&output=rss

      RSSDigester dig = new RSSDigester();

      ChannelBuilder builder = new ChannelBuilder();

      ChannelIF channel = FeedParser.parse(builder,feed);
      
      channel.setFormat(ChannelFormat.RSS_2_0);

      for (Iterator iter = channel.getItems().iterator(); iter.hasNext();) {
        ItemIF item = (ItemIF)iter.next();
        lijst.add(item);

        //        result.put(item.getTitle(), item);
      }
    } catch (MalformedURLException mue) {
      mue.printStackTrace();
    } catch (Exception pe) {
      pe.printStackTrace();
    }

    return lijst;
  }

}
