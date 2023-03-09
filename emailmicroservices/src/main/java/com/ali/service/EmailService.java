package com.ali.service;

import com.ali.mapper.IEmailMapper;
import com.ali.rabbitmq.model.MailCreate;
import com.ali.repository.IEamilRepository;
import com.ali.repository.entity.Email;
import com.ali.utility.ServiceManager;
import com.ali.utility.TokenGenerator;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService extends ServiceManager<Email, Long> {
    private final IEamilRepository emailRepository;
    private final MailSenderService mailSenderService;
    public EmailService(IEamilRepository emailRepository, MailSenderService mailSenderService) {
        super(emailRepository);
        this.emailRepository = emailRepository;
        this.mailSenderService = mailSenderService;
    }

    public void save(MailCreate mailCreate) {
        Email email = IEmailMapper.INSTANCE.toEmail(mailCreate);
        email.setCode(TokenGenerator.generateCode());
        save(email);
        mailSenderService.sendMail(email);
    }

}
