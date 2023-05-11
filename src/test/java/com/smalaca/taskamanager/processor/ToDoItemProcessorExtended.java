package com.smalaca.taskamanager.processor;

import com.smalaca.taskamanager.model.interfaces.ToDoItem;
import com.smalaca.taskamanager.registry.EventsRegistry;
import com.smalaca.taskamanager.service.CommunicationService;
import com.smalaca.taskamanager.service.ProjectBacklogService;
import com.smalaca.taskamanager.service.SprintBacklogService;
import com.smalaca.taskamanager.service.StoryService;

class ToDoItemProcessorExtended extends ToDoItemProcessor {

    public ToDoItemProcessorExtended(StoryService storyService, EventsRegistry eventsRegistry, ProjectBacklogService projectBacklogService, CommunicationService communicationService, SprintBacklogService sprintBacklogService) {
        super(storyService, eventsRegistry, projectBacklogService, communicationService, sprintBacklogService);
    }

    public void processDefined(ToDoItem toDoItem) {
        super.processDefined(toDoItem);
    }
}
