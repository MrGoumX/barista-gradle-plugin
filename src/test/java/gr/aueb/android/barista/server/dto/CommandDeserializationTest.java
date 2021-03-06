package gr.aueb.android.barista.server.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommandDeserializationTest {

    GeoFixDTO geoFix;
    WmSizeDTO wmSize;
    WmSizeResetDTO wmSizeReset;
    GpsStatusDTO gpsStatusDTO;

    String geoFixJson = "{\"type\":\"GeoFix\",\"sessionToken\":\"1\",\"delay\":0,\"latitude\":1.0,\"longitude\":2.0}";
    String wmSizeJson = "{\"type\":\"WmSize\",\"sessionToken\":\"2\",\"delay\":0,\"width\":1280,\"height\":800,\"reset\":false,\"unit\":\"DPI\"}";
    String wmSizeResetJson = "{\"type\":\"WmSizeReset\",\"sessionToken\":\"3\",\"delay\":\"0\"}";

    ObjectMapper objectMapper;

    @Before
    public void setup(){

        geoFix = new GeoFixDTO("1", 1.0, 2.0);
        wmSize = new WmSizeDTO("2", 1280, 800, false, "DPI");
        wmSizeReset = new WmSizeResetDTO("3");
        gpsStatusDTO = new GpsStatusDTO("4", false);

        objectMapper = new ObjectMapper();

    }

    @Test
    public void testPolymorphicDeserialization() throws IOException {
        System.out.println(objectMapper.writeValueAsString(geoFix));
        assertThat(objectMapper.writeValueAsString(geoFix), is(equalTo(geoFixJson)));
        assertThat(objectMapper.writeValueAsString(wmSize), is(equalTo(wmSizeJson)));

        CommandDTO geofixCommand = objectMapper.readValue(geoFixJson, CommandDTO.class);
        assertThat(geofixCommand instanceof GeoFixDTO, is(true));

        CommandDTO wmSizeCommand = objectMapper.readValue(wmSizeJson, CommandDTO.class);
        assertThat(wmSizeCommand instanceof WmSizeDTO, is(true));

        CommandDTO wmSizeResetCommand = objectMapper.readValue(wmSizeResetJson, CommandDTO.class);
        assertThat(wmSizeCommand instanceof WmSizeDTO, is(true));


    }



}
