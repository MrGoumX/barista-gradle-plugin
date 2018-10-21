/**
 * Author: Tsiskomichelis Stelios
 * Created On: 17/10/2018
 * Project: android-plugin
 * <p>
 * ClassName: TestDispacherServerTask
 * Role: Implements the task that deploys the adb test dispacher
 * Description:
 */
package core;

import org.gradle.api.Action;
import org.gradle.api.DefaultTask;
import org.gradle.api.logging.LogLevel;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import server.HttpServerManager;
import sun.rmi.runtime.Log;
import sun.util.resources.ga.LocaleNames_ga;

public class TestDispacherServerTask extends DefaultTask {
    private final String DEFAULT_SERVER_URL = "http://localhost:8080";

    private String serverURL = DEFAULT_SERVER_URL;

    @Input
    public String getServerURL(){
        return serverURL;
    }

    public void setServerURL(String serverURL){
        this.serverURL = serverURL;
    }

    @TaskAction
    public void deployServer(){

        getProject().getLogger().log(LogLevel.LIFECYCLE,this.getPath());
        //getProject().getLogger().log(LogLevel.);
        System.out.println("Deploying Server at: "+serverURL);
        HttpServerManager.startServer();
    }

    @TaskAction
    public void stopServer(){
        System.out.println("Shuting down Server at: "+serverURL);
        HttpServerManager.stopServer();
    }


}
