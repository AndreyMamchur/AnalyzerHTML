
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Andrey Mamchur
 */
public class Main {

    public static void main(String[] args) {

        switch (args.length) {
            case (0):
                System.out.println("You forgot to enter arguments!");
                break;
            case (1):
                System.out.println("You have entered only one argument. The minimum number of arguments is 2.");
                break;
            case (2):
                Utill.writeDataToFile("OriginalPage.xml", Utill.readHTMLPage(args[0]));
                Utill.writeDataToFile("SamplePage.xml", Utill.readHTMLPage(args[1]));
                Element buttonOpt = Utill.findElementById(new File("OriginalPage.xml"), "make-everything-ok-button");
                //
                List<Element> listOfElement = new ArrayList<>();
                for (Attribute a : buttonOpt.attributes().asList()) {
                    Elements el = Utill.findElementsByQuery(new File("SamplePage.xml"), "a[" + a + "]");
                    for (int i = 0; i < el.size(); i++) {
                        listOfElement.add(el.get(i));
                    }
                }

                List<HTMLElement> listElements = new ArrayList<>();
                for (Element e : listOfElement) {
                    boolean needContinue = false;
                    for (HTMLElement h : listElements) {
                        if (h.getElement().equals(e)) {
                            needContinue=true;
                            break;
                        }
                    }
                    if (needContinue){
                        continue;
                    }
                    int count = 0;
                    for (Element j : listOfElement) {
                        if (e.equals(j)) {
                            count++;
                        }
                    }
                    listElements.add(new HTMLElement(e, count));

                }
                Collections.sort(listElements);
                Element myElement = listElements.get(0).getElement();
                System.out.println("desired item: " + myElement);
                System.out.println("patch to desired item: " + myElement.cssSelector());                        
                break;

            default:
                System.out.println("You have entered more arguments than necessary");
                break;
        }

    }

}
