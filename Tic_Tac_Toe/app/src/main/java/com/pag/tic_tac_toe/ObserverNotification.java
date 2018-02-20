package com.pag.tic_tac_toe;

public class ObserverNotification {

    private static final int STARTUP = 0, PLAYER_MOVE = 1, COM_MOVE = 2, IGNORE = 3;

    private int notification;
    private Object[] datas;

    public ObserverNotification() {
        this.notification = IGNORE;
    }

    public ObserverNotification(int notification, Object[] datas) {
        this.notification = notification;
        this.datas = datas;
    }

    public int getNotification() {
        return notification;
    }

    public Object[] getDatas() {
        return datas;
    }

    public void setNotification(int notification) {
        this.notification = notification;
    }

    public void setDatas(Object[] datas) {
        this.datas = datas;
    }
}
