/*
 * FezBook.java
 * @version 0.1.2
 * @author Adrian J. Cruz
 * @license http://www.opensource.org/licenses/bsd-license.php <2012>, <Adrian J. Cruz> 
 */
package FezBook;

import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class FezBook {
    private static final String FBGRAPH = "https://graph.facebook.com/";
    private static final String ME = "https://graph.facebook.com/me/";
    private String token;

    /*
     * constructor
     * @param String atoken
     */
    public FezBook(String atoken) {
        this.token = atoken;
    }

    /*
     * Opens a URI
     * @param String uri
     * @return Bufferedreader b the URI content
     */
    public BufferedReader openuri(String uri) throws MalformedURLException {
        BufferedReader b = null;
        try {
            URL u = new URL(uri);
            try {
                b = new BufferedReader(new InputStreamReader(u.openStream()));
            }
            catch (IOException e) {
                System.err.printf("IOException: %s\n", e.getMessage());
            }
        }
        catch (MalformedURLException e) {
            System.err.printf("MalformedURLException: %s\n", e.getMessage());
        }
        return b;
    }

    /*
     * Gets the Facebook accounts
     * @param long fbid Facebook ID
     * @return String line the JSON returned that contains the account information
     */
    public String get_accounts(long fbid) {
        String uri = this.FBGRAPH + fbid + "/accounts?access_token=" + this.token;
        String line = null;
        try {
            BufferedReader b = this.openuri(uri);
            try {
                line = b.readLine();
            }
            catch (IOException e) {
                System.err.printf("IOException: %s\n", e.getMessage());
            }
        }
        catch (MalformedURLException e) {
            System.err.printf("MalformedURLException: %s\n", e.getMessage());
        }
        return line;
    }

    /*
     * Gets the Facebook accounts
     * @return String line the JSON returned that contains the account information
     */
    public String get_accounts() {
        String uri = this.ME + "accounts?access_token=" + this.token;
        String line = null;
        try {
            BufferedReader b = this.openuri(uri);
            try {
                line = b.readLine();
            }
            catch (IOException e) {
                System.err.printf("IOException: %s\n", e.getMessage());
            }
        }
        catch (MalformedURLException e) {
            System.err.printf("MalformedURLException: %s\n", e.getMessage());
        }
        return line;
    }

    /*
     * Get Facebook info
     * @param long fbid Facebook ID
     * @return String line the JSON returned that contains information for fbid
     */
    public String get_fbdata(long fbid) {
        String uri = this.FBGRAPH + fbid + "?access_token=" + this.token;
        String line = null;
        try {
            BufferedReader b = this.openuri(uri);
            try {
                line = b.readLine();
            }
            catch (IOException e) {
                System.err.printf("IOException: %s\n", e.getMessage());
            }
        }
        catch (MalformedURLException e) {
            System.err.printf("MalformedURLException: %s\n", e.getMessage());
        }
        return line;
    }

    /*
     * Get Facebook info
     * @return String line the JSON returned that contains information for ME
     */
    public String get_fbdata() {
        String uri = this.ME + "?access_token=" + this.token;
        String line = null;
        try {
            BufferedReader b = this.openuri(uri);
            try {
                line = b.readLine();
            }
            catch (IOException e) {
                System.err.printf("IOException: %s\n", e.getMessage());
            }
        }
        catch (MalformedURLException e) {
            System.err.printf("MalformedURLException: %s\n", e.getMessage());
        }
        return line;
    }

    /*
     * Get Facebook feed
     * @param long fbid Facebook ID
     * @return String line the JSON returned that contains the feed information
     */
    public String get_feed(long fbid) {
        String uri = this.FBGRAPH + fbid + "/feed&access_token=" + this.token;
        String line = null;
        try {
            BufferedReader b = this.openuri(uri);
            try {
                line = b.readLine();
            }
            catch (IOException e) {
                System.err.printf("IOException: %s\n", e.getMessage());
            }
        }
        catch (MalformedURLException e) {
            System.err.printf("MalformedURLException: %s\n", e.getMessage());
        }
        return line;
    }

    /*
     * Get Facebook feed
     * @return String line the JSON returned that contains the feed information
     */
    public String get_feed() {
        String uri = this.ME + "feed&access_token=" + this.token;
        String line = null;
        try {
            BufferedReader b = this.openuri(uri);
            try {
                line = b.readLine();
            }
            catch (IOException e) {
                System.err.printf("IOException: %s\n", e.getMessage());
            }
        }
        catch (MalformedURLException e) {
            System.err.printf("MalformedURLException: %s\n", e.getMessage());
        }
        return line;
    }

    /*
     * Post to Facebook feed
     * @param String postdata URLEncode'd data
     * @return String line the JSON returned that contains the post information
     */
    public String post_to_feed(String postdata) {
        String uri = this.ME + "feed?access_token=" + this.token;
        String line = null;
        try {
            /* Send data */
            URL url = new URL(uri);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(postdata);
            wr.flush();

            /* Get the response */
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            line = rd.readLine();
            wr.close();
            rd.close();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return line;
    }

    /*
     * Post to Facebook feed
     * @param String postdata URLEncode'd data
     * @param long fbid Facebook ID
     * @return String line the JSON returned that contains the post information
     */
    public String post_to_feed(String postdata, long fbid) {
        String uri = this.FBGRAPH + fbid + "/feed?access_token=" + this.token;
        String line = null;
        try {
            /* Send data */
            URL url = new URL(uri);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(postdata);
            wr.flush();

            /* Get the response */
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            line = rd.readLine();
            wr.close();
            rd.close();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return line;
    }
}
