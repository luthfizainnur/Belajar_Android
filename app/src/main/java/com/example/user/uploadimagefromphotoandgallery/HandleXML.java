package com.example.user.uploadimagefromphotoandgallery;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 22/07/2016.
 */
public class HandleXML {
    private String code = "INVOICE_TYPE_LOOKUP_CODE";
    private String num = "INVOICE_NUM";
    private String date = "INVOICE_DATE";
    private String amount = "INVOICE_AMOUNT";
    private String currency = "INVOICE_CURRENCY_CODE";

    private String urlString = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;

    public  HandleXML(String url){
        this.urlString = url;
    }

    public String getCode(){
        return code;
    }

    public String getNum(){
        return num;
    }

    public String getDate(){
        return date;
    }

    public String getAmount(){
        return amount;
    }

    public String getCurrency(){
        return currency;
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser){
        int event;
        String text = null;

        try {
            event = myParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();

                switch (event){
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(name.equals("INVOICE_TYPE_LOOKUP_CODE")){
                            code = text;
                        }

                        else if(name.equals("INVOICE_NUM")){
                            num = text;
                        }

                        else if(name.equals("INVOICE_DATE")){
                            date = text;
                        }

                        else if(name.equals("INVOICE_AMOUNT")){
                            amount = text;
                        }

                        else if(name.equals("INVOICE_CURRENCY_CODE")){
                            currency = text;
                        }
                        else{

                        }
                        break;
                }
                event = myParser.next();
            }
            parsingComplete = false;
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream stream = conn.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);

                    parseXMLAndStoreIt(myparser);
                    stream.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
