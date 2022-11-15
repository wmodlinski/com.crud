package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@RequiredArgsConstructor
public class MailCreatorService {

    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    private AdminConfig adminConfig;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://wmodlinski.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "Kind regards!");
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("preview", "New card added!");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}