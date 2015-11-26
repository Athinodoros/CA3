package xml;

import com.google.gson.Gson;
import entity.Currency;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class XmlReaderDemo extends DefaultHandler implements Callable<List<Currency>> {

    private ArrayList<Currency> listOfCurrency = new ArrayList<>();

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
        Currency c = new Currency();
        for (int i = 0; i < attributes.getLength(); i++) {

            if (attributes.getQName(i).equals("code")) {
                c.setCode(attributes.getValue(i));
            } else if (attributes.getQName(i).equals("desc")) {
                c.setDesc(attributes.getValue(i));
            } else if (attributes.getQName(i).matches("rate")) {
                try {
                    c.setRate(Double.valueOf(attributes.getValue(i)));
                } catch (Exception e) {
                    c.setRate(0.00);
                }
            } else {

            }

            if (c.getCode()!= null) {
                listOfCurrency.add(c);
            }

        }
    }

    public static void main(String[] args) throws Exception {
        XmlReaderDemo x = new XmlReaderDemo();
        Gson g = new Gson();
        System.out.println(g.toJson(x.call()));
    }

    public ArrayList<Currency> getListOfCurrency() {

        return listOfCurrency;
    }

    public void setListOfCurrency(ArrayList<Currency> listOfCurrency) {
        this.listOfCurrency = listOfCurrency;
    }

    @Override
    public List<Currency> call() throws Exception {
        XmlReaderDemo reader = new XmlReaderDemo();
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(reader);
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return reader.listOfCurrency;
    }

}
