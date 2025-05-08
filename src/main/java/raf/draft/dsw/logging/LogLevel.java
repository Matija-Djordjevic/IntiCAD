package raf.draft.dsw.logging;

public enum LogLevel{
    ERROR(){
        @Override
        public String toString(){ return "GRESKA"; }
    },
    WARNING {
        @Override
        public String toString(){ return "UPOZORENJE"; }
    },
    INFO {
        @Override
        public String toString(){ return "OBAVESTENJE"; }
    },
    UNKNOWN {
        @Override
        public String toString(){ return "NEPOZNAT_NIVO_GRESKE"; }
    },
    FATAL {
        @Override
        public String toString() { return "FATALNA_GRESKA"; }
    },
    DEBUG {
        @Override
        public String toString() { return "FATALNA_GRESKA"; }
    }
}
