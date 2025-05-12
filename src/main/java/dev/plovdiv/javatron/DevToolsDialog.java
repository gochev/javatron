package dev.plovdiv.javatron;

import org.cef.browser.CefBrowser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DevToolsDialog extends JDialog {
    private final CefBrowser cefDevTools;

    public DevToolsDialog(JFrame owner, String title, CefBrowser browser) {
        this(owner, title, browser, null);
    }

    public DevToolsDialog(JFrame owner, String title, CefBrowser browser,
                          Point inspectAt) {
        super(owner, title, false);

        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocation(owner.getLocation().x + 20, owner.getLocation().y + 20);

        cefDevTools = browser.getDevTools(inspectAt);
        add(cefDevTools.getUIComponent());

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void dispose() {
        cefDevTools.close(true);
        super.dispose();
    }
}
