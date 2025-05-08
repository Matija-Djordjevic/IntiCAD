package raf.draft.dsw.gui.swing;

import lombok.NoArgsConstructor;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.Gui;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.Building;
import raf.draft.dsw.models.Project;
import raf.draft.dsw.models.structure.CompositeSpacialModel;

@NoArgsConstructor
public class SwingGui implements Gui {
    @Override
    public void start() {
        MainFrame.getInstance().setVisible(true);
    }
}
