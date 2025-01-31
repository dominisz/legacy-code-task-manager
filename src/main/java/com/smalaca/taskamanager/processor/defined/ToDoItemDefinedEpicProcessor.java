package com.smalaca.taskamanager.processor.defined;

import com.smalaca.taskamanager.events.EpicReadyToPrioritize;
import com.smalaca.taskamanager.model.entities.Epic;
import com.smalaca.taskamanager.model.entities.Story;
import com.smalaca.taskamanager.registry.EventsRegistry;
import com.smalaca.taskamanager.service.CommunicationService;
import com.smalaca.taskamanager.service.ProjectBacklogService;
import org.springframework.stereotype.Component;

@Component
class ToDoItemDefinedEpicProcessor {

  private final ProjectBacklogService projectBacklogService;
  private final EventsRegistry eventsRegistry;
  private final CommunicationService communicationService;

  public ToDoItemDefinedEpicProcessor(
      ProjectBacklogService projectBacklogService,
      EventsRegistry eventsRegistry,
      CommunicationService communicationService) {
    this.projectBacklogService = projectBacklogService;
    this.eventsRegistry = eventsRegistry;
    this.communicationService = communicationService;
  }

  void process(Epic epic) {
    projectBacklogService.putOnTop(epic);
    EpicReadyToPrioritize event = new EpicReadyToPrioritize();
    event.setEpicId(epic.getId());
    eventsRegistry.publish(event);
    communicationService.notify(epic, epic.getProject().getProductOwner());
  }
}
