/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rs.representation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author lucacambiaghi
 */
public abstract class Representation {

    @XmlElement(name = "link")
    private List<Link> links = new ArrayList<Link>();

    public List<Link> getLinks() {
        return links;
    }

    public Link getLinkByRelation(String uriName) {
        if (links == null) {
            return null;
        }
        for (Link l : links) {
            if (l.getRel().toLowerCase().equals(uriName.toLowerCase())) {
                return l;
            }
        }
        return null;
    }
}
