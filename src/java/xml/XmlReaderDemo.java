package xml;

import com.google.gson.Gson;
import entity.Currency;
import entity.DateEntity;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class XmlReaderDemo extends DefaultHandler implements Callable<List<Currency>> {

    private DateEntity de = new DateEntity();;
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

        if (attributes.getQName(0).equals("id")) {

            
            de.setDate(Date.valueOf(attributes.getValue(0)));
            c.setCurrencyCode(attributes.getValue(0));
        }
        if (attributes.getQName(0).equals("code")) {

            c.setCurrencyCode(attributes.getValue(0));
        }
        if (attributes.getQName(1) != null) {
            if (attributes.getQName(1).equals("desc")) {
                //System.out.println(attributes.getValue(1));
                c.setDescription(attributes.getValue(1));

            }
        }
        if (attributes.getQName(2) != null) {
            if (attributes.getQName(2).matches("rate")) {
                try {
                    c.setRate(Double.valueOf(attributes.getValue(2)));
                } catch (Exception e) {
                    c.setRate(0.00);
                }
            }
        } else {

        }

        if (c.getDescription() != null) {
            c.setDate(de);

            this.listOfCurrency.add(c);
        }

    }

    public static void main(String[] args) throws Exception {
        XmlReaderDemo x = new XmlReaderDemo();
        Gson g = new Gson();
        System.out.println(deploy.DeploymentConfiguration.PU_NAME);
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(deploy.DeploymentConfiguration.PU_NAME);
        EntityManager em = emf.createEntityManager();
        System.out.println(reader.listOfCurrency.get(0));
        try {
            em.getTransaction().begin();
            em.persist(reader.de);
            for (Currency item : reader.listOfCurrency) {
                em.persist(item);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            em.close();
            emf.close();
        }

        return reader.listOfCurrency;
    }

}
