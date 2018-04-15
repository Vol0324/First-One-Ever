package certificate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class GetPicture {
    private final String original_path = "http://www.airliners.net/search?aircraftBasicType=8633";
    private String random_path = null;

    private HttpURLConnection connection = null;
    private BufferedReader br = null;
    private InputStream is = null;
    private String result = null;

    private ArrayList<String> picture_site = new ArrayList<>();
    private ArrayList<String> info_pic = new ArrayList<>();
    private String jpg_file = null;

    public void randomPage(){
        random_path = original_path + "&page=" + ((int)(800 * Math.random()) + 1);
        setPictureSite();
    }

    private void setPictureSite(){
        //Get random pictures;
        String s = doGet(random_path);
        ArrayList<String> urls = new ArrayList<>();
        Document doc = Jsoup.parse(s);
        Elements elements = doc.select("div[class=ps-v2-results-photo]");
        for(Element e : elements){
            urls.add(e.select("a").attr("href"));
        }
        for(int i = 0; i < urls.size(); i ++){
            String url_modified = urls.get(i);
            url_modified = "http://www.airliners.net" + url_modified;
            picture_site.add(url_modified);
        }
    }

    private String getRandomPictureSite(){
        int random_pic = (int) (36 * Math.random());
        String specific_url = picture_site.get(random_pic);
        return specific_url;
    }

    public void getInfo(){
        //Get pictures information
        Document doc2 ;
        Elements element4 ;
        while(true) {
            doc2 = Jsoup.parse(doGet(getRandomPictureSite()));
            element4 = doc2.select("div.pib-section-content").select("a[href]");
            if(element4.size() == 13)
                break;
        }
        int j = 0;
        for(Element e : element4){
            if(j > 6)
                break;
            j++;
            info_pic.add(e.text());
        }

        //Get pictures .jpg path
        Elements element5 = doc2.select("div.photo-details-photo").select("div.pdp-image-wrapper")
                .select("img");
        String temp = null;
        for(Element element : element5){
            temp = element.attr("src");
        }
        jpg_file = temp.substring(0, temp.indexOf("jpg") + 3);
    }

    public ArrayList<String> getInfo_pic() {
        return info_pic;
    }

    public String getJpg_file() {
        return jpg_file;
    }

    private String doGet(String path) {
        try {
            URL url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(60000);
            connection.connect();
            is = connection.getInputStream();
            if (connection.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(
                        is, "UTF-8"));
                ArrayList<String> s = new ArrayList<>();
                while ((result = br.readLine()) != null) {
                    s.add(result);
                }
                result = null;
                Iterator<String> itr = s.iterator();
                while(itr.hasNext()){
                    result += itr.next();
                    result += "\n";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            connection.disconnect();
        }

        return result;
    }
}
