package com.example.notification_microservice.services.Implementation;

import com.example.eligibility_microservice.common.LetterEvents.LetterEligibleEvent;
import com.example.eligibility_microservice.common.PackageEvents.PackageEligibleEvent;
import com.example.notification_microservice.services.Interface.INotificationService;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class NotificationService implements INotificationService {

    private final TemplateEngine templateEngine;
    private final MailgunService mailgunService;
    private final S3Service s3Service;

    public NotificationService(TemplateEngine templateEngine, MailgunService mailgunService, S3Service s3Service) {
        this.templateEngine = templateEngine;
        this.mailgunService = mailgunService;
        this.s3Service = s3Service;
    }

    @Override
    public void processLetter(LetterEligibleEvent event) {
        Context context = new Context();

        context.setVariable("trackingNumber", event.getTrackingNumber());
        context.setVariable("subject", event.getSubject());
        String html = templateEngine.process("letter-notification", context);

        mailgunService.sendEmail(event.getReceiverEmail(), "Letter notification", html).subscribe();
    }

    @Override
    public void processPackage(PackageEligibleEvent event) {
        Context context = new Context();

        context.setVariable("trackingNumber", event.getTrackingNumber());
        context.setVariable("content", event.getContent());
        String html = templateEngine.process("package-notification", context);
        String s3key = s3Service.uploadHtml(event.getTrackingNumber().toString(), html);

        System.out.println("Template stored in S3: " + s3key);

        mailgunService.sendEmail(event.getReceiverEmail(), "Package notification", html).subscribe(
                unused -> {},
                error -> System.err.println("Error sending email: " + error.getMessage())
        );
    }
}
