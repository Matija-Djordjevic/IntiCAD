package raf.draft.dsw.gui.swing.controller;

import raf.draft.dsw.Main;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public abstract class AbstractDraftAction extends AbstractAction {
    public Icon loadIcon(String fileName){
        URL imageURL = getClass().getResource(fileName);
        if (imageURL == null) { System.err.println("Resource not found: " + fileName); return null; }

        ImageIcon originalIcon = new ImageIcon(imageURL);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage
                // TODO dodaj u config widht heigt
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Set desired size

        return new ImageIcon(scaledImage);
    }
}
