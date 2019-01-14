package com.panchen.martinet.plugin.exec;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "checkLog", defaultPhase = LifecyclePhase.PACKAGE)
public class DefaultCollectLogExecutor extends AbstractMojo implements CollectLogExecutor

{

    @Parameter(defaultValue = "${project.directory}", property = "collectDir", required = true)
    private String collectDir;

    public void execute() throws MojoExecutionException {
 
        System.out.println(collectDir);

    }

    @Override
    public CollectLogResponse executeCollectLog(CollectLogRequest checkLogRequest) {
        
        return null;
    }

}
