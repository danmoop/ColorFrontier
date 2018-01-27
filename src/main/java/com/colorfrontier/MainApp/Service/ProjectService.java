package com.colorfrontier.MainApp.Service;

import com.colorfrontier.MainApp.Model.Project;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectService
{
    Set<Project> awaitingProjects = new HashSet<>();

    Set<Project> acceptedProjects = new HashSet<>();

    public Set<Project> getAwaitingProjects()
    {
        return awaitingProjects;
    }

    public Set<Project> getAcceptedProjects()
    {
        return acceptedProjects;
    }

    public void acceptProject(Project project)
    {
        awaitingProjects.remove(project);
        acceptedProjects.add(project);
    }

    public void newProjectForReview(Project project)
    {
        awaitingProjects.add(project);
    }
}