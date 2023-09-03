package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks; ///clienti
    private AtomicInteger waitingPeriod; ///timpul de asteptare total al cozii

    public Server(int maxTasksServer) {
        this.tasks = new ArrayBlockingQueue<>(maxTasksServer);
        this.waitingPeriod = new AtomicInteger(0);

    }
    public void addTask(Task task) {
        this.tasks.add(task);
        this.waitingPeriod.addAndGet(task.getServiceTime());
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (tasks.size() > 0) {
                    Task nextTask = tasks.peek();
                    int serviceTime = nextTask.getServiceTime();
                    while (serviceTime > 0) {
                        Thread.sleep(1000);
                        waitingPeriod.getAndDecrement();
                        nextTask.setServiceTime(serviceTime - 1);
                        serviceTime = nextTask.getServiceTime();
                    }
                    tasks.remove(nextTask);
                }


            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

}
