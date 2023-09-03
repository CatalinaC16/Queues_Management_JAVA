package businessLogic;

import gui.Frame;
import model.Server;
import model.Task;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class SimulationManager implements Runnable {
    private int timeMaxSimulare;
    private int timpMinServire;
    private int timpMaxServire;
    private int timpMinSosire;
    private int timpMaxSosire;
    private int noServers;
    private int noTasks;
    private SelectionPolicy selected;
    private Scheduler scheduler;
    private BlockingQueue<Task> tasks;
    private Frame frame;
    private FileWriter fisier;

    public SimulationManager(Frame frame, int timeMaxSimulare, int timpMinServire, int timpMaxServire, int timpMinSosire, int timpMaxSosire, int noServers, int noTasks, SelectionPolicy sel) throws IOException {
        this.frame = frame;
        this.timeMaxSimulare = timeMaxSimulare;
        this.timpMinServire = timpMinServire;
        this.timpMaxServire = timpMaxServire;
        this.timpMinSosire = timpMinSosire;
        this.timpMaxSosire = timpMaxSosire;
        this.noServers = noServers;
        this.noTasks = noTasks;
        this.selected = sel;
        this.fisier = new FileWriter("simulation.txt");
        this.scheduler = new Scheduler(noServers, noTasks, selected);
        this.tasks = generatedRandom();
    }

    public BlockingQueue<Task> generatedRandom() {
        BlockingQueue<Task> tasks = new ArrayBlockingQueue<>(noTasks);
        for (int i = 0; i < noTasks; i++) {
            Random rand = new Random();
            int sosire = rand.nextInt(timpMaxSosire - timpMinSosire + 1) + timpMinSosire;
            int servire = rand.nextInt(timpMaxServire - timpMinServire + 1) + timpMinServire;
            Task task = new Task(i + 1, sosire, servire);
            tasks.add(task);
        }
        List<Task> taskList = new ArrayList<>(tasks);
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return Integer.compare(task1.getArrivalTime(), task2.getArrivalTime());
            }
        });
        tasks = new ArrayBlockingQueue<>(noTasks, true, taskList);
        return tasks;
    }

    public void afisareTasksAsteptare() throws IOException {
        this.frame.getAsteptareClienti().setText("");
        this.fisier.write("Clienti care trebuie pusi la coada:");
        for (Task task : tasks) {
            this.frame.getAsteptareClienti().setText(frame.getAsteptareClienti().getText() + "(" + task.getID() + "," + task.getArrivalTime() + "," + task.getServiceTime() + ")\n");
            this.fisier.write("(" + task.getID() + "," + task.getArrivalTime() + "," + task.getServiceTime() + ")");
        }
    }

    public int peakTime(List<Server> servers) {
        int maxxNrPerTime = servers.get(0).getTasks().size();
        int auxNrTasks=0;
        for (Server server : servers) {
            auxNrTasks += server.getTasks().size();
            if (auxNrTasks > maxxNrPerTime) {
                maxxNrPerTime = auxNrTasks;
            }
        }
        return maxxNrPerTime;
    }

    public double timeSpent(List<Server> servers, Task taskSearched) {
        double finalAverageTime = 0.0;
        for (Server server : servers) {
            for (Task task : server.getTasks()) {
                if (task.getServiceTime() > 0 && taskSearched.equals(task)) {
                    BlockingQueue<Task> queue = server.getTasks();
                    List<Task> subList = new ArrayList<>(queue);

                    int index = subList.indexOf(task);
                    if (index >= 1) {
                        List<Task> previousTasks = subList.subList(0, index);

                        AtomicInteger totalTimeSpent = new AtomicInteger(0);
                        for (Task prevTask : previousTasks) {
                            totalTimeSpent.addAndGet(prevTask.getServiceTime());
                        }
                        finalAverageTime += totalTimeSpent.get();
                    }
                }
            }
        }
        return finalAverageTime;
    }

    public void afisareCozi(int timp, List<Server> servers) throws IOException {
        this.frame.getCoziClienti().setText("Timp: " + timp + "\n");
        this.fisier.write("\nTimp " + timp + "\n");
        for (int i = 0; i < servers.size(); i++) {
            Server server = servers.get(i);
            if (server.getTasks().size() == 0) {
                this.frame.getCoziClienti().setText(frame.getCoziClienti().getText() + "Coada " + (i + 1) + " -> inchisa");
                this.fisier.write("Coada " + (i + 1) + " -> inchisa");
            } else {
                this.frame.getCoziClienti().setText(frame.getCoziClienti().getText() + "Coada " + (i + 1) + " -> ");
                this.fisier.write("Coada " + (i + 1) + " -> ");
            }
            System.out.println(i + " -> ");
            for (Task task : server.getTasks()) {
                System.out.println(task);
                this.frame.getCoziClienti().setText(frame.getCoziClienti().getText() + "(" + task.getID() + "," + task.getArrivalTime() + "," + task.getServiceTime() + ")");
                this.fisier.write("(" + task.getID() + "," + task.getArrivalTime() + "," + task.getServiceTime() + ") ");
            }
            this.frame.getCoziClienti().setText(frame.getCoziClienti().getText() + "\n");
            this.fisier.write("\n");
        }
        this.fisier.write("\n");
    }

    public void writeResults(double timpAsteptare, double timpProcesare, int peakTime) throws IOException {
        this.fisier.write("\nTimpul mediu de asteptare este " + timpAsteptare/ (double) noTasks);
        this.fisier.write("\nTimpul mediu de procesare este " + timpProcesare / (double) noTasks);
        this.fisier.write("\nTimpul de varf este " + peakTime);
        this.frame.getAvgTimeAsteptare().setText(Double.toString(timpAsteptare/ (double) noTasks));
        this.frame.getAvgTimeProcesare().setText(Double.toString(timpProcesare/ (double) noTasks));
        this.frame.getPeakTime().setText(Integer.toString(peakTime));
    }
    @Override
    public void run() {
        AtomicInteger timp = new AtomicInteger(0);
        int peakTime = 0, maxTasks = 0;
        double averageTimeSpent = 0.0, averageTimeService = 0.0;
        int auxTime = Integer.parseInt(frame.getTimpSimulare().getText());
        Iterator<Task> iter;
        frame.getSTART().setEnabled(false);
        while (timp.get() <= timeMaxSimulare) {
            iter = tasks.iterator();
            while (iter.hasNext()) {
                Task nextTask = iter.next();
                if (nextTask.getArrivalTime() == timp.get()) {
                    scheduler.dispatchTask(nextTask);
                    averageTimeSpent += timeSpent(scheduler.getServers(), nextTask);
                    averageTimeService += (timeSpent(scheduler.getServers(), nextTask) + nextTask.getServiceTime());
                    this.tasks.remove(nextTask);
                }
            }
            try {
                if (timp.get() <= auxTime) {
                    afisareCozi(timp.get(), scheduler.getServers());
                    if(peakTime(scheduler.getServers()) > maxTasks){
                        maxTasks = peakTime(scheduler.getServers());
                        peakTime = timp.get();
                    }
                    afisareTasksAsteptare();
                    Thread.sleep(1000);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            timp.addAndGet(1);
        }
        try {
            writeResults(averageTimeSpent,averageTimeService,peakTime);
            this.fisier.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        frame.getSTART().setEnabled(true);
    }
}
