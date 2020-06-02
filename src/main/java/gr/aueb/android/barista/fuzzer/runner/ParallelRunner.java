package gr.aueb.android.barista.fuzzer.runner;

import gr.aueb.android.barista.core.executor.CommandExecutor;
import gr.aueb.android.barista.core.executor.CommandExecutorFactory;
import gr.aueb.android.barista.core.executor.CommandExecutorImpl;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.core.model.LogcatCrash;
import gr.aueb.android.barista.core.model.SvcData;
import gr.aueb.android.barista.core.model.SvcWifi;
import gr.aueb.android.barista.fuzzer.ContextEventGenerator;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.List;

public class ParallelRunner implements Runner {

    private List<Command> monkeyCommands;
    private ContextEventGenerator eventGenerator;
    private CommandExecutor executor;
    private LogcatCrash crashReporter;
    private boolean stop;

    public ParallelRunner(List<Command> monkeyCommands, ContextEventGenerator eventGenerator, LogcatCrash crashReporter) {
        this.monkeyCommands = monkeyCommands;
        this.eventGenerator = eventGenerator;
        this.executor = (CommandExecutorImpl) CommandExecutorFactory.getCommandExecutor();
        this.crashReporter = crashReporter;
    }

    @Override
    public void start() {
        for (Command cmd : this.monkeyCommands) {
            this.stop = false;
            synchronized (this) {
                this.executor.executeCommand(cmd);
                this.executor.executeCommand(this.crashReporter);
                if (this.crashReporter.hasCrashed()) {
                    this.stop = true;
                    break;
                }
                new Thread(() -> {
                    while (!this.stop) {
                        Command ctx = this.eventGenerator.generateSingle();
                        this.executor.executeCommand(ctx);
                        try {
                            if (ctx instanceof SvcWifi || ctx instanceof SvcData) {
                                Thread.sleep(5000);
                            }
                            else {
                                Thread.sleep(1500);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.executor.executeCommand(this.crashReporter);
                        if (this.crashReporter.hasCrashed()) {
                            this.stop = true;
                            break;
                        }
                    }
                }).start();
                this.stop = true;
            }
        }
        this.executor.executeCommand(this.crashReporter);
        BaristaLogger.printList(this.crashReporter.getCrashLog());
        BaristaLogger.writeCrashLog(this.crashReporter.getCrashLog());
    }

}
