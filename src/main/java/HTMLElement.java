
import java.util.logging.Logger;
import org.jsoup.nodes.Element;

/**
 *
 * @author Andrey Mamchur
 */
public class HTMLElement implements Comparable<HTMLElement>{
    private Element element;
    private int count;

    public HTMLElement() {
    }
    
    public HTMLElement(Element element, int count) {
        this.element = element;
        this.count = count;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(HTMLElement o) {

        if (this.getCount() > o.getCount()){
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "HTMLElement{" + "element=" + element + ", count=" + count + '}';
    }
        
}
