package gui;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class Frame extends JFrame {
    private JTextField noCozi, noClienti, timpSimulare, timpSosireMax, timpSosireMin, timpServireMin, timpServireMax;
    private JTextField avgTimeAsteptare, avgTimeProcesare, peakTime;
    private JTextArea coziClienti, asteptareClienti;
    private JLabel cozi, clienti, servireMax, servireMin, simulare, sosireMin, sosireMax, avgTimeT, avgTimeT2, peakTimeT;
    private JRadioButton timp, coada;
    private JButton START;
    private Controller controller;
    private ButtonGroup buttonGroup;

    public Frame() {
        controller = new Controller(this);
        initializareFrame();
    }

    public void initializareFrame() {
        this.setBackground(new Color(128, 186, 221));
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(140, 192, 223));
        this.setBounds(320, 5, 700, 700);
        this.getContentPane().setLayout(null);
        this.setResizable(false);
        labels();
        text();
        radioButtons();
        butonStart();
        this.setVisible(true);
    }

    public void butonStart() {
        this.START = new JButton("START");
        this.START.setBackground(new Color(44, 117, 160));
        this.START.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        this.START.setForeground(new Color(14, 38, 52));
        this.START.setBounds(474, 129, 100, 33);
        this.START.setActionCommand("Start");
        this.START.addActionListener(this.controller);
        this.getContentPane().add(START);
    }

    public void text() {
        this.noCozi = new JTextField();
        this.noCozi.setForeground(new Color(14, 38, 52));
        this.noCozi.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        this.noCozi.setBackground(new Color(180, 215, 235));
        this.noCozi.setBounds(108, 22, 57, 28);
        this.getContentPane().add(noCozi);
        this.noCozi.setColumns(10);

        this.noClienti = new JTextField();
        this.noClienti.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        this.noClienti.setForeground(new Color(14, 38, 52));
        this.noClienti.setBackground(new Color(162, 204, 230));
        this.noClienti.setColumns(10);
        this.noClienti.setBounds(108, 73, 57, 28);
        this.getContentPane().add(noClienti);

        this.timpSimulare = new JTextField();
        this.timpSimulare.setForeground(new Color(14, 38, 52));
        this.timpSimulare.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        this.timpSimulare.setColumns(10);
        this.timpSimulare.setBackground(new Color(162, 204, 230));
        this.timpSimulare.setBounds(153, 129, 57, 28);
        this.getContentPane().add(timpSimulare);

        this.timpSosireMax = new JTextField();
        this.timpSosireMax.setForeground(new Color(14, 38, 52));
        this.timpSosireMax.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        this.timpSosireMax.setColumns(10);
        this.timpSosireMax.setBackground(new Color(162, 204, 230));
        this.timpSosireMax.setBounds(348, 73, 57, 28);
        this.getContentPane().add(timpSosireMax);

        this.timpSosireMin = new JTextField();
        this.timpSosireMin.setForeground(new Color(14, 38, 52));
        this.timpSosireMin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        this.timpSosireMin.setColumns(10);
        this.timpSosireMin.setBackground(new Color(162, 204, 230));
        this.timpSosireMin.setBounds(348, 22, 57, 28);
        this.getContentPane().add(timpSosireMin);

        this.timpServireMax = new JTextField();
        this.timpServireMax.setForeground(new Color(14, 38, 52));
        this.timpServireMax.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        this.timpServireMax.setColumns(10);
        this.timpServireMax.setBackground(new Color(162, 204, 230));
        this.timpServireMax.setBounds(596, 76, 57, 28);
        this.getContentPane().add(timpServireMax);

        this.timpServireMin = new JTextField();
        this.timpServireMin.setForeground(new Color(14, 38, 52));
        this.timpServireMin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        this.timpServireMin.setColumns(10);
        this.timpServireMin.setBackground(new Color(162, 204, 230));
        this.timpServireMin.setBounds(596, 22, 57, 28);
        this.getContentPane().add(timpServireMin);

        this.coziClienti = new JTextArea();
        this.coziClienti.setForeground(new Color(14, 38, 52));
        this.coziClienti.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        this.coziClienti.setColumns(10);
        this.coziClienti.setBackground(new Color(162, 204, 230));
        this.coziClienti.setBounds(29, 198, 309, 339);
        this.getContentPane().add(coziClienti);

        this.asteptareClienti = new JTextArea();
        this.asteptareClienti.setForeground(new Color(14, 38, 52));
        this.asteptareClienti.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        this.asteptareClienti.setColumns(10);
        this.asteptareClienti.setBackground(new Color(162, 204, 230));
        this.asteptareClienti.setBounds(348, 198, 305, 339);
        this.getContentPane().add(asteptareClienti);

        this.avgTimeAsteptare = new JTextField();
        this.avgTimeAsteptare.setForeground(new Color(14, 38, 52));
        this.avgTimeAsteptare.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        this.avgTimeAsteptare.setColumns(10);
        this.avgTimeAsteptare.setBackground(new Color(162, 204, 230));
        this.avgTimeAsteptare.setBounds(258, 569, 161, 28);
        this.getContentPane().add(avgTimeAsteptare);

        this.avgTimeProcesare = new JTextField();
        this.avgTimeProcesare.setForeground(new Color(14, 38, 52));
        this.avgTimeProcesare.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        this.avgTimeProcesare.setColumns(10);
        this.avgTimeProcesare.setBackground(new Color(162, 204, 230));
        this.avgTimeProcesare.setBounds(258, 613, 161, 28);
        this.getContentPane().add(avgTimeProcesare);

        this.peakTime = new JTextField();
        this.peakTime.setForeground(new Color(14, 38, 52));
        this.peakTime.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        this.peakTime.setColumns(10);
        this.peakTime.setBackground(new Color(162, 204, 230));
        this.peakTime.setBounds(466, 605, 169, 28);
        this.getContentPane().add(peakTime);

        this.setVisible(true);

    }

    public void labels() {
        this.cozi = new JLabel("Nr. cozi:");
        this.cozi.setForeground(new Color(26, 69, 96));
        this.cozi.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 18));
        this.cozi.setBounds(10, 22, 78, 20);
        this.getContentPane().add(cozi);

        this.clienti = new JLabel("Nr. clienti:");
        this.clienti.setForeground(new Color(26, 69, 96));
        this.clienti.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 18));
        this.clienti.setBounds(10, 73, 100, 28);
        this.getContentPane().add(clienti);

        this.simulare = new JLabel("Timp simulare:");
        this.simulare.setForeground(new Color(26, 69, 96));
        this.simulare.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 18));
        this.simulare.setBounds(10, 129, 133, 28);
        this.getContentPane().add(simulare);

        this.sosireMin = new JLabel("Timp sosire min:");
        this.sosireMin.setForeground(new Color(26, 69, 96));
        this.sosireMin.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 18));
        this.sosireMin.setBounds(190, 22, 148, 20);
        this.getContentPane().add(sosireMin);

        this.sosireMax = new JLabel("Timp sosire max:");
        this.sosireMax.setForeground(new Color(26, 69, 96));
        this.sosireMax.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 18));
        this.sosireMax.setBounds(190, 81, 148, 20);
        this.getContentPane().add(sosireMax);

        this.servireMin = new JLabel("Timp servire min:");
        this.servireMin.setForeground(new Color(26, 69, 96));
        this.servireMin.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 18));
        this.servireMin.setBounds(425, 22, 148, 20);
        this.getContentPane().add(servireMin);

        this.servireMax = new JLabel("Timp servire max:");
        this.servireMax.setForeground(new Color(26, 69, 96));
        this.servireMax.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 18));
        this.servireMax.setBounds(425, 81, 148, 20);
        this.getContentPane().add(servireMax);

        this.avgTimeT = new JLabel("Timp mediu de asteptare:");
        this.avgTimeT.setForeground(new Color(26, 69, 96));
        this.avgTimeT.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 18));
        this.avgTimeT.setBounds(29, 566, 219, 28);
        this.getContentPane().add(avgTimeT);

        this.avgTimeT2 = new JLabel("Timp mediu de procesare:");
        this.avgTimeT2.setForeground(new Color(26, 69, 96));
        this.avgTimeT2.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 18));
        this.avgTimeT2.setBounds(29, 605, 219, 28);
        this.getContentPane().add(avgTimeT2);

        this.peakTimeT = new JLabel("Ora/timpul de varf:");
        this.peakTimeT.setForeground(new Color(26, 69, 96));
        this.peakTimeT.setFont(new Font("Yu Gothic UI", Font.BOLD | Font.ITALIC, 18));
        this.peakTimeT.setBounds(466, 566, 169, 28);
        this.getContentPane().add(peakTimeT);
    }

    public void radioButtons() {
        this.timp = new JRadioButton("Cel mai scurt timp");
        this.timp.setBackground(new Color(162, 204, 230));
        this.timp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        this.timp.setForeground(new Color(21, 57, 79));
        this.timp.setBounds(245, 135, 139, 23);
        this.getContentPane().add(timp);

        this.coada = new JRadioButton("Cea mai scurta coada");
        this.coada.setForeground(new Color(21, 57, 79));
        this.coada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        this.coada.setBackground(new Color(162, 204, 230));
        this.coada.setBounds(244, 158, 161, 23);
        this.getContentPane().add(coada);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(timp);
        buttonGroup.add(coada);

    }

    public JTextArea getCoziClienti() {
        return coziClienti;
    }

    public void setCoziClienti(JTextArea coziClienti) {
        this.coziClienti = coziClienti;
    }

    public JTextArea getAsteptareClienti() {
        return asteptareClienti;
    }

    public void setAsteptareClienti(JTextArea asteptareClienti) {
        this.asteptareClienti = asteptareClienti;
    }

    public JTextField getNoCozi() {
        return noCozi;
    }

    public void setNoCozi(JTextField noCozi) {
        this.noCozi = noCozi;
    }

    public JTextField getNoClienti() {
        return noClienti;
    }

    public void setNoClienti(JTextField noClienti) {
        this.noClienti = noClienti;
    }

    public JTextField getTimpSimulare() {
        return timpSimulare;
    }

    public void setTimpSimulare(JTextField timpSimulare) {
        this.timpSimulare = timpSimulare;
    }

    public JTextField getTimpSosireMax() {
        return timpSosireMax;
    }

    public void setTimpSosireMax(JTextField timpSosireMax) {
        this.timpSosireMax = timpSosireMax;
    }

    public JTextField getTimpSosireMin() {
        return timpSosireMin;
    }

    public void setTimpSosireMin(JTextField timpSosireMin) {
        this.timpSosireMin = timpSosireMin;
    }

    public JTextField getTimpServireMin() {
        return timpServireMin;
    }

    public void setTimpServireMin(JTextField timpServireMin) {
        this.timpServireMin = timpServireMin;
    }

    public JTextField getTimpServireMax() {
        return timpServireMax;
    }

    public void setTimpServireMax(JTextField timpServireMax) {
        this.timpServireMax = timpServireMax;
    }

    public JRadioButton getTimp() {
        return timp;
    }

    public void setTimp(JRadioButton timp) {
        this.timp = timp;
    }

    public JRadioButton getCoada() {
        return coada;
    }

    public void setCoada(JRadioButton coada) {
        this.coada = coada;
    }

    public JButton getSTART() {
        return START;
    }

    public void setSTART(JButton START) {
        this.START = START;
    }

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public void setButtonGroup(ButtonGroup buttonGroup) {
        this.buttonGroup = buttonGroup;
    }

    public JTextField getAvgTimeAsteptare() {
        return avgTimeAsteptare;
    }

    public void setAvgTimeAsteptare(JTextField avgTimeAsteptare) {
        this.avgTimeAsteptare = avgTimeAsteptare;
    }

    public JTextField getAvgTimeProcesare() {
        return avgTimeProcesare;
    }

    public void setAvgTimeProcesare(JTextField avgTimeProcesare) {
        this.avgTimeProcesare = avgTimeProcesare;
    }

    public JTextField getPeakTime() {
        return peakTime;
    }

    public void setPeakTime(JTextField peakTime) {
        this.peakTime = peakTime;
    }
}
