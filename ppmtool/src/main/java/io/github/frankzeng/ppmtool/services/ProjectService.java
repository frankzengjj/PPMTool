package io.github.frankzeng.ppmtool.services;

import io.github.frankzeng.ppmtool.domain.Project;
import io.github.frankzeng.ppmtool.exceptions.ProjectIdException;
import io.github.frankzeng.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {

        // Logic
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID: "+project.getProjectIdentifier().toUpperCase()+" already exists");
        }
    }

    public  Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(null == project) {
            throw new ProjectIdException("Project ID' "+projectId.toUpperCase()+"' does not exist");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(null == project) {
            throw new ProjectIdException("Cannot find project with Id '"+projectId.toUpperCase()+"' ");
        }

        projectRepository.delete(project);
    }

}
