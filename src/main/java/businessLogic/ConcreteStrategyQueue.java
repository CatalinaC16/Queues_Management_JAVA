package businessLogic;

import model.Server;
import model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task newTask) {
        Server server = servers.get(0);
        int minQueue = servers.get(0).getTasks().size();
        for (Server it : servers) {
            int size = it.getTasks().size();
            if (minQueue > size) {
                minQueue = size;
                server = it;
            }
        }
        server.addTask(newTask);
    }
}
