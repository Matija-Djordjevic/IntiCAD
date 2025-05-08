package raf.draft.dsw.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.config.Config;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.factories.commands.roomelements.IRoomElementCommandsFactory;
import raf.draft.dsw.factories.commands.roomelements.RoomElementCommandsFactory;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.tree.controllers.mouselisteners.DoubleClickMouseListener;
import raf.draft.dsw.gui.swing.tree.controllers.mouselisteners.SingleClickMouseListener;
import raf.draft.dsw.gui.swing.tree.controllers.mouselisteners.TripleClickMouseListener;
import raf.draft.dsw.gui.swing.tree.models.DraftTree;
import raf.draft.dsw.gui.swing.tree.models.DraftTreeNode;
import raf.draft.dsw.gui.swing.tree.view.DraftJTree;
import raf.draft.dsw.gui.swing.tree.view.TreeActionManager;
import raf.draft.dsw.utils.JsonSerializationUtils;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

@Getter
@Setter
public class MainFrame extends JFrame {
    static MainFrame instance;
    TreeActionManager actionManager;
    JMenuBar menu;
    JToolBar myToolBar;
    DefaultTreeModel treeModel;
    JTree treeView;
    JPanel projectView;
    IRoomElementCommandsFactory roomElementCommandsFactory;

    public static MainFrame getInstance() {
        if (instance != null) return instance;

        instance = new MainFrame();
        return instance.initialise();
    }

    private MainFrame() { }

    private MainFrame initialise() {

        actionManager = new TreeActionManager();
        roomElementCommandsFactory = new RoomElementCommandsFactory(ApplicationFramework.getInstance().getMessageGenerator());

        var projectExplorerModel = ApplicationFramework.getInstance().getProjectManager().getProjectExplorer();
        var projectExplorerNode = new DraftTreeNode(projectExplorerModel);
        treeModel = new DraftTree(projectExplorerNode);
        var mapping = ((DraftTree)treeModel).getMapping();
        mapping.put(projectExplorerModel, projectExplorerNode);
        treeView = new DraftJTree(treeModel);

        treeView.addMouseListener(new TripleClickMouseListener());
        treeView.addMouseListener(new DoubleClickMouseListener());
        treeView.addMouseListener(new SingleClickMouseListener());

        initialiseGUI();

        return this;
    }

    private void initialiseGUI() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Draft Room");

        menu = new MyMenuBar();
        setJMenuBar(menu);

        myToolBar = new MyToolBar();
        add(myToolBar, BorderLayout.NORTH);

        projectView = new ProjectView();

        JScrollPane scroll = new JScrollPane(treeView);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, projectView);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { onApplicationExit(); }
        });
    }

    private void onApplicationExit() {
        var config = Config.getInstance();
        JsonSerializationUtils.saveToFile(config, new File("config.json"));
    }
}
