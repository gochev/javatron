package dev.plovdiv.javatron;

import java.io.IOException;
import java.net.URL;

import javax.swing.JOptionPane;

import me.friwi.jcefmaven.CefInitializationException;
import me.friwi.jcefmaven.UnsupportedPlatformException;

public class JavatronApplication {
    public static void startApp(String[] args) {
        //Print some info for the test reports. You can ignore this.

        // The simple example application is created as anonymous class and points
        // to Google as the very first loaded page. Windowed rendering mode is used by
        // default. If you want to test OSR mode set |useOsr| to true and recompile.
        boolean useOsr = false;
        try {
            URL indexUrl = JavatronApplication.class.getClassLoader().getResource("index.html");
            if (indexUrl == null) {
                JOptionPane.showMessageDialog(null, "please create index.html in the resources folder");
            }
            new BrowserFrame("file://" + indexUrl.getFile(), useOsr, false, args);
        } catch (UnsupportedPlatformException | CefInitializationException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Unsupported Platform");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "index.html not found");
        }
    }
}
