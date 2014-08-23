package org.aurigone.emi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by anthony on 23.08.14.
 */
public class JSONReader {
    protected JSONObject getJsonObject(InputStream stream) throws IOException, JSONException {
        byte[] buffer = new byte[stream.available()];
        stream.read(buffer);
        return new JSONObject(new String(buffer, "UTF-8"));
    }
}
