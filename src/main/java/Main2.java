import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import raf.draft.dsw.compatibility.FinalChecker;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.Gui;
import raf.draft.dsw.gui.swing.SwingGui;
import raf.draft.dsw.models.Building;
import raf.draft.dsw.models.Project;
import raf.draft.dsw.models.ProjectManager;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.utils.SpiralMatrixDrawer;

public class Main2 {
    public static void main(String[] args) throws JsonProcessingException {
        ApplicationFramework appCore = ApplicationFramework.getInstance();

        Gui gui = new SwingGui();
        var draftRepository = new ProjectManager();

        var checker = new FinalChecker();

        appCore.initialise(gui, draftRepository, checker);
        appCore.run();


        var pm = ApplicationFramework.getInstance().getProjectManager();
        var pe = pm.getProjectExplorer();

        var p1 = new Project("p1", pe);
        pm.add(pe, p1);

        var b1 = new Building("b1", p1);
        pm.add(p1, b1);
        var r1 = new Room("r1", b1);
        pm.add(b1, r1);
        var r2 = new Room("r2", b1);
        pm.add(b1, r2);
        var r3 = new Room("r3", b1);
        pm.add(b1, r3);

        var b2 = new Building("b2", p1);
        pm.add(p1, b2);
        var r4 = new Room("r4", b2);
        pm.add(b2, r4);
        var r5 = new Room("r5", b2);
        pm.add(b2, r5);


        var p2 = new Project("p2", pe);
        pm.add(pe, p2);

        var b3 = new Building("b3", p1);
        pm.add(p2, b3);

        var b4 = new Building("b4", p1);
        pm.add(p2, b4);
        var r6 = new Room("r6", b4);
        pm.add(b4, r6);
        var r7 = new Room("r7", b4);
        pm.add(b4, r7);
    }

    static void test4by4(){
        var mat = SpiralMatrixDrawer.generateSpiralMatrix(4, 4);
        SpiralMatrixDrawer.printMatrix(mat);
    }
}
