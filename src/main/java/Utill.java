import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author Andrey Mamchur
 */
public class Utill {
    private static String CHARSET_NAME = "utf8"; 

    /**
     *
     * @param url The path to the desired page
     * @return html code from page
     */
    public static String readHTMLPage(String url){

        try{
            StringBuilder stringBuilder = new StringBuilder();
            URL page = new URL(url);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(page.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            br.close();
            return stringBuilder.toString();
        }catch(MalformedURLException me){
            System.err.println("Unknown host: " + me);
            System.exit(0);
        }catch(IOException ioe){
            System.err.println("Input error: " + ioe);
        }
        return "";
    }
    
    /**
     * 
     * @param patchToFile
     * @param data 
     */
    public static void writeDataToFile(String patchToFile, String data) {
        try (PrintWriter outputStream = new PrintWriter(new FileOutputStream(patchToFile))) {
            outputStream.println(data);
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }
    
    /**
     * 
     * @param htmlFile
     * @param targetElementId
     * @return 
     */
    public static Element findElementById(File htmlFile, String targetElementId) {
        try {
            Document doc = Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath());
            

            return doc.getElementById(targetElementId);

        } catch (IOException e) {           
            System.err.println("Error reading [{}] file " + htmlFile.getAbsolutePath() + e);
            return null;
        }
    }
    
    /**
     * 
     * @param htmlFile
     * @param cssQuery
     * @return 
     */
    public static Elements findElementsByQuery(File htmlFile, String cssQuery) {
        try {
            Document doc = Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath());

            return doc.select(cssQuery);

        } catch (IOException e) {
            System.err.println("Error reading [{}] file " + htmlFile.getAbsolutePath() + e);
            return null;
        }
    }
    
}
