package raf.draft.dsw.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.draft.dsw.compatibility.SpacialModelsCompatibilityChecker;
import raf.draft.dsw.config.Config;
import raf.draft.dsw.gui.Gui;
import raf.draft.dsw.logging.*;
import raf.draft.dsw.models.MessageGenerator;
import raf.draft.dsw.models.Project;
import raf.draft.dsw.models.ProjectManager;

@Setter
@Getter
public class ApplicationFramework {
    private static ApplicationFramework instance;

    protected Gui gui;
    protected ProjectManager projectManager;

    public SpacialModelsCompatibilityChecker checker;
    public MessageGenerator messageGenerator;

    public static ApplicationFramework getInstance() {
        return instance == null
                ? instance = new ApplicationFramework()
                : instance;
    }

    public void run(){ this.gui.start(); }

    public void initialise(Gui gui,
                           ProjectManager draftRepository,
                           SpacialModelsCompatibilityChecker checker) {
        this.gui = gui;
        this.projectManager = draftRepository;

        this.checker = checker;
        messageGenerator = new MessageGenerator();

        var loggers = new ChainedLoggers(
                new FileLogger(),
                new ConsoleLogger());

        messageGenerator.add(loggers);
    }
}
