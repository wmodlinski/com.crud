package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private DbService dbService;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");


        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://wmodlinski.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "Kind regards!");
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("preview", "New card added!");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }


    public String buildTasksStatusMail() {
        List<Task> openTasks = dbService.getAllTasks();
        Context context = new Context();
        context.setVariable("admin_config", adminConfig);
        context.setVariable("message", "Currently you have "+ openTasks.size() + " open tasks");
        context.setVariable("open_tasks", openTasks);
        context.setVariable("preview", "Tasks status");
        context.setVariable("goodbye", "Kind regards!");
        return templateEngine.process("mail/tasks-status-mail", context);
    }

}