package org.iesfm.airline;

import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[]{
                "org.iesfm.airline.**"
        });
        xStream.processAnnotations(Airline.class);
        xStream.processAnnotations(Flight.class);
        xStream.processAnnotations(Passenger.class);

        try {
            Airline airline = (Airline) xStream.fromXML(new File(Airline.class.getResource("/iberia.xml").toURI()));
            log.info(airline.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
