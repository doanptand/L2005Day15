package com.t3h.myapplication.parser;

import com.t3h.myapplication.model.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class NewsParser {


    public List<News> parseNews(String link) {
        List<News> result = new ArrayList<>();
        try {
            URL url = new URL(link);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = factory.newPullParser();
            pullParser.setInput(inputStream, "utf-8");

            int type = pullParser.getEventType();
            String text = "";
            News news = null;

            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        String startTag = pullParser.getName();
                        if (startTag.equals("item")) {
                            news = new News();
                        } else if (startTag.equals("img")) {
                            String avatar = pullParser.getAttributeValue(null, "src");
//                            String avatar = pullParser.getAttributeValue(0);
                            news.setAvatar(avatar);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = pullParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (news == null) {
                            break;
                        }
                        String endTag = pullParser.getName();
                        if (endTag.equals("item")) {
                            result.add(news);
                        } else if (endTag.equals("title")) {
                            news.setTitle(text);
                        } else if (endTag.equals("description")) {
                            news.setDescription(text);
                        } else if (endTag.equals("link")) {
                            news.setDetailLink(text);
                        } else if (endTag.equals("pubDate")) {
                            news.setPubDate(text);
                        }
                        break;
                }
                type = pullParser.next();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return result;
    }
}
