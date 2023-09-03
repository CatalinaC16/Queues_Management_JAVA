package gui;

import businessLogic.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller implements ActionListener {
    private Frame frame;
    private SimulationManager simulare;
    private Thread thread;

    public Controller(Frame frame) {
        this.frame = frame;
    }

    public void simulareNoua() {
        //citirea datelor din interfata
        AtomicInteger noTasks, noServers, timpSimulare, timpSosireMax, timpSosireMin, timpServireMin, timpServireMax;
        SelectionPolicy selected = null;
        int aux;

        noTasks = new AtomicInteger(0);
        noServers = new AtomicInteger(0);
        timpSimulare = new AtomicInteger(0);
        timpServireMin = new AtomicInteger(0);
        timpServireMax = new AtomicInteger(0);
        timpSosireMin = new AtomicInteger(0);
        timpSosireMax = new AtomicInteger(0);

        try {
            aux = Integer.parseInt(frame.getNoCozi().getText());
            noServers.set(aux);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.frame, "Numarul de cozi nu este corect!", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            aux = Integer.parseInt(frame.getNoClienti().getText());
            noTasks.addAndGet(aux);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.frame, "Numarul de clienti nu este corect!", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            aux = Integer.parseInt(frame.getTimpSimulare().getText());
            timpSimulare.set(aux);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.frame, "Timpul de simulare nu este corect!", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            aux = Integer.parseInt(frame.getTimpSosireMin().getText());
            timpSosireMin.set(aux);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.frame, "Timpul de sosire minim nu este corect!", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            aux = Integer.parseInt(frame.getTimpSosireMax().getText());
            timpSosireMax.set(aux);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.frame, "Timpul de sosire maxim nu este corect!", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            aux = Integer.parseInt(frame.getTimpServireMin().getText());
            timpServireMin.set(aux);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.frame, "Timpul de servire minim nu este corect!", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            aux = Integer.parseInt(frame.getTimpServireMax().getText());
            timpServireMax.set(aux);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this.frame, "Timpul de servire maxim nu este corect!", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }
        if (frame.getCoada().isSelected()) {
            selected = SelectionPolicy.SHORTEST_QUEUE;
        } else if (frame.getTimp().isSelected()) {
            selected = SelectionPolicy.SHORTEST_TIME;
        } else {
            JOptionPane.showMessageDialog(this.frame, "Nu ai selectat strategia!", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }
        ///pornirea simularii
        try {
            simulare = new SimulationManager(this.frame, timpSimulare.get(), timpServireMin.get(), timpServireMax.get(), timpSosireMin.get(), timpSosireMax.get(), noServers.get(), noTasks.get(), selected);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        thread = new Thread(simulare);
        thread.start();
}

    @Override
    public void actionPerformed(ActionEvent e) {

        String comanda = e.getActionCommand();
        if (Objects.equals(comanda, "Start")) {
            simulareNoua();
        }
    }
}
