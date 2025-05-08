package raf.draft.dsw.logging;


import raf.draft.dsw.utils.LogUtils;

public class ConsoleLogger implements IMessageListener<LogInfo>{
    @Override
    public void onMessageReceived(LogInfo message) { System.out.println(LogUtils.getLogMsg(message)); }
}
