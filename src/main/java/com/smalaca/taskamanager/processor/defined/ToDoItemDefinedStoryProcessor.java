package com.smalaca.taskamanager.processor.defined;

import com.smalaca.taskamanager.model.entities.Story;
import com.smalaca.taskamanager.service.CommunicationService;
import com.smalaca.taskamanager.service.ProjectBacklogService;
import org.springframework.stereotype.Component;

@Component
class ToDoItemDefinedStoryProcessor {

  private final ProjectBacklogService projectBacklogService;
  private final CommunicationService communicationService;

  public ToDoItemDefinedStoryProcessor(
      ProjectBacklogService projectBacklogService, CommunicationService communicationService) {
    this.projectBacklogService = projectBacklogService;
    this.communicationService = communicationService;
  }

  void process(Story story) {
    if (story.getTasks().isEmpty()) {
      projectBacklogService.moveToReadyForDevelopment(story, story.getProject());
    } else {
      if (!story.isAssigned()) {
        communicationService.notifyTeamsAbout(story, story.getProject());
      }
    }
  }
}
