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

    private DateEntity de;
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
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(deploy.DeploymentConfiguration.PU_NAME);
            EntityManager em = emf.createEntityManager();
            de = new DateEntity();
            de.setDate(Date.valueOf(attributes.getValue(0)));

            try {
                em.getTransaction().begin();
                em.persist(de);
                em.getTransaction().commit();
            } catch (Exception e) {
            } finally {
                em.close();
                emf.close();
            }
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

            listOfCurrency.add(c);
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
        System.out.println("XXX1");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(deploy.DeploymentConfiguration.PU_NAME);
        System.out.println("hfkjsdfhkjs");
        EntityManager em = emf.createEntityManager();
        System.out.println("XXX2");
        
        try {
            em.getTransaction().begin();
//            System.out.println("XXX3");
            for (Currency listOfCurrency1 : listOfCurrency) {
                em.persist(listOfCurrency1);
            }
        } catch (Exception e) {
        } finally {
//            System.out.println("XXX4");
            em.getTransaction().commit();
            em.close();
            emf.close();
//            System.out.println("XXX5");
        }

        return reader.listOfCurrency;
    }

}
