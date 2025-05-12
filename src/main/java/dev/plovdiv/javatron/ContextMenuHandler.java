package dev.plovdiv.javatron;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefContextMenuParams;
import org.cef.callback.CefMenuModel;
import org.cef.callback.CefMenuModel.MenuId;
import org.cef.handler.CefContextMenuHandler;

import javax.swing.*;


public class ContextMenuHandler implements CefContextMenuHandler {

    private JFrame browserFrame = null;

    ContextMenuHandler(JFrame browserFrame) {
        this.browserFrame = browserFrame;
    }


    @Override
    public void onBeforeContextMenu(CefBrowser cefBrowser, CefFrame cefFrame, CefContextMenuParams cefContextMenuParams, CefMenuModel cefMenuModel) {
        cefMenuModel.clear();

        // Navigation menu
        cefMenuModel.addItem(MenuId.MENU_ID_BACK, "Back");
        cefMenuModel.setEnabled(MenuId.MENU_ID_BACK, cefBrowser.canGoBack());

        cefMenuModel.addItem(MenuId.MENU_ID_FORWARD, "Forward");
        cefMenuModel.setEnabled(MenuId.MENU_ID_FORWARD, cefBrowser.canGoForward());

        cefMenuModel.addSeparator();
//        cefMenuModel.addItem(MenuId.MENU_ID_FIND, "Find...");
//        if (cefContextMenuParams.hasImageContents() && cefContextMenuParams.getSourceUrl() != null)
//            cefMenuModel.addItem(MenuId.MENU_ID_USER_FIRST, "Download Image...");
        cefMenuModel.addItem(MenuId.MENU_ID_VIEW_SOURCE, "View Source...");
        cefMenuModel.addItem(301, "Inspect");
    }

    @Override
    public boolean onContextMenuCommand(CefBrowser cefBrowser, CefFrame cefFrame, CefContextMenuParams cefContextMenuParams, int commandId,
                                        int eventFlags) {
        switch (commandId) {
//            case MenuId.MENU_ID_USER_FIRST:
//                cefBrowser.startDownload(cefContextMenuParams.getSourceUrl());
//                return true;
            case 301:
                DevToolsDialog inspectDialog = new DevToolsDialog(browserFrame, "Dev tools", cefBrowser);
                inspectDialog.setVisible(true);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onContextMenuDismissed(CefBrowser cefBrowser, CefFrame cefFrame) {

    }
}
