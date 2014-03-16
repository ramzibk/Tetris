/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bkramzi.tetris.tetrismvc.model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

/**
 *
 * @author ramzi
 */
public class AbstractModel {
    private final EventListenerList listenerList = new EventListenerList();
    private final ChangeEvent stateChangeEvent = new ChangeEvent(this);

    public void addChangeListener(ChangeListener l) {
    listenerList.add(ChangeListener.class, l);
    }
    public void removeChangeListener(ChangeListener l) {
    listenerList.remove(ChangeListener.class, l);
    }
    protected void fireChange() {
        for (ChangeListener l: listenerList.getListeners(ChangeListener.class)) {
            l.stateChanged(stateChangeEvent);
        }
    }

}
