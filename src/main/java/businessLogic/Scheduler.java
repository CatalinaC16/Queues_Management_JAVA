package businessLogic;

import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer, SelectionPolicy sel) {
        this.servers = new ArrayList<>();
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        changeStrategy(sel);

        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server(maxTasksPerServer);
            Thread thread = new Thread(server);
            thread.start();
            servers.add(server);
        }
    }

    public void changeStrategy(SelectionPolicy sel) {
        switch (sel) {
            case SHORTEST_QUEUE:
                this.strategy = new ConcreteStrategyQueue();
                break;
            case SHORTEST_TIME:
                this.strategy = new ConcreteStrategyTime();
                break;
            default:
                throw new IllegalArgumentException("Nu exista alta strategie");
        }
    }

    public void dispatchTask(Task task) {
        this.strategy.addTask(servers, task);
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public int getMaxNoServers() {
        return maxNoServers;
    }

    public void setMaxNoServers(int maxNoServers) {
        this.maxNoServers = maxNoServers;
    }

    public int getMaxTasksPerServer() {
        return maxTasksPerServer;
    }

    public void setMaxTasksPerServer(int maxTasksPerServer) {
        this.maxTasksPerServer = maxTasksPerServer;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

}
