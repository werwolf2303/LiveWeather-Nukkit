package com.liveweather.test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.liveweather.GlobalValues;
import com.liveweather.client.Client;
import com.liveweather.commandline.LWLogging;
import com.liveweather.report.Report;
import com.liveweather.storage.LWConfig;
import com.liveweather.updater.Update;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Properties;

import static org.apache.logging.log4j.core.impl.ThrowableFormatOptions.FILE_NAME;
import static org.eclipse.jetty.client.GZIPContentDecoder.DEFAULT_BUFFER_SIZE;

public class Test {
    public static void main(String[] args) {
        GlobalValues.debug = true;
        byte[] valueDecoded = Base64.decodeBase64("Z2hwX3NrNWZkbXlXMDdpa1FqU3kwY0tLanB1WVg3dkp0aDFkZ3dONg==");
        System.out.println("Decoded value is " + new String(valueDecoded));
    }
}