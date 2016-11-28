/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import resource.HotelResource;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import resource.HotelResource;

/**
 *
 * @author lucacambiaghi
 */
public class DateParser {
    
    public static XMLGregorianCalendar parse(String date){
        
        int year = Integer.parseInt(date.split("-")[2]);
        int month = Integer.parseInt(date.split("-")[1]);
        int day = Integer.parseInt(date.split("-")[0]);
        
        XMLGregorianCalendar xmlDate = null;
        try {
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(year, month, day));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(HotelResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return xmlDate;
        
    }
    
}
