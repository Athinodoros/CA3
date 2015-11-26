package xml;

import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;

public class XmlReaderDemo extends DefaultHandler {
    @Override
    public void startDocument() throws SAXException {
// System.out.println("[");
    }

    @Override
    public void endDocument() throws SAXException {
// System.out.println("]");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
// System.out.print("("  );
// for (int i = 0; i < attributes.getLength(); i++) {
// System.out.print("'" +attributes.getQName(i)+"':'"+ attributes.getValue(i)+"',");
// }
// System.out.println("}");
        StringBuilder jsonStringBuilder = new StringBuilder();
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.getQName(i).equals("code")) {
                jsonStringBuilder.append("{'" + attributes.getQName(i) + "':'" + attributes.getValue(i) + "',");
            } else if (attributes.getQName(i).equals("desc")) {
                jsonStringBuilder.append("'" + attributes.getQName(i) + "':'" + attributes.getValue(i) + "',");
            } else if (attributes.getQName(i).equals("rate")) {
                jsonStringBuilder.append("'" + attributes.getQName(i) + "':'" + attributes.getValue(i) + "'}");

            }
        }
        System.out.println(jsonStringBuilder);
    }

    public static void main(String[] argv) {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReaderDemo());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
