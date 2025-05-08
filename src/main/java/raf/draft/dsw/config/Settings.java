package raf.draft.dsw.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Settings {
    @JsonProperty("useUndoRedoTree")
    boolean useUndoRedoTree;

    @JsonProperty("logging")
    boolean logging;
}
