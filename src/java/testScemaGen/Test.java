/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testScemaGen;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 *
 * @author Athinodoros
 */
public class Test {
    public static void main(String[] args) {
        Persistence.generateSchema("PU-Local", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU-Local");
    }
}
