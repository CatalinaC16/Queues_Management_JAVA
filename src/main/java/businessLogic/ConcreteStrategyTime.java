package businessLogic;

import model.Server;
import model.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task newTask) {
        Server server = servers.get(0);
        AtomicInteger minWaitTime = servers.get(0).getWaitingPeriod();
        for (Server it : servers) {
            AtomicInteger time = it.getWaitingPeriod();
            if (minWaitTime.get() > time.get()) {
                minWaitTime = time;
                server = it;
            }
        }
        server.addTask(newTask);
    }
}
