package com.azarenka.service;

import com.azarenka.domain.MailProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class MailService {

    private final static String SECOND_MAIL_BODY =
        "<p>%s %s is up and running on P_DEV. %s %s production release.</p>" +
            "<p>Release notes can be found here: <a href='%s'>%s</a>";
    private final static String FIRST_MAIL_BODY = "<p>We are going to deploy %s %s to P_DEV.</p>" +
        "<p>We will send additional email once it is done.</p>";
    @Value("C:\\Program Files\\Microsoft Office\\root\\Office16\\OUTLOOK.EXE")
    private String outlookPath;
    @Autowired
    private PropertiesService propertiesService;

    public void generateMail(String application, String version, boolean firstMail) {
        MailProperty mailProperty = propertiesService.loadProperty();
        Path reportPath = null;
        try {
            reportPath = Files.createTempFile("report", "report.html");
            print(propertiesService.loadProperty(), reportPath, firstMail
                ? String.format(FIRST_MAIL_BODY, application, version)
                : String.format(SECOND_MAIL_BODY, application, version, mailProperty.getMonthRelease(),
                mailProperty.getYearRelease(), buildLink(mailProperty, application),
                buildLink(mailProperty, application)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        openGeneratedMailInOutlook(application, version, reportPath, mailProperty);
    }

    /**
     * Generates report and opens it in Outlook app.
     */
    public void openGeneratedMailInOutlook(String applicationName, String version, Path reportPath,
                                           MailProperty mailProperty) {
        try {
            new ProcessBuilder(outlookPath, "/c", "ipm.note",
                "/a", reportPath.toAbsolutePath().toString(),
                "/m", String.format("%s?cc=%s&subject=%s", mailProperty.getToEmail(), mailProperty.getCcEmail(),
                String.format(mailProperty.getSubject(), applicationName, version)))
                .start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes mail to a file in HTML format.
     *
     * @param mailProperty instance of {@link MailProperty}
     * @param path         {@link Path} to file
     * @throws IOException if an I/O error occurs writing to or creating the file
     */
    public void print(MailProperty mailProperty, Path path, String mailBody) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format("<p>%s</p>", mailProperty.getHeader()))
            .append(mailBody)
            .append("<p>Best regards,</p>");
        Files.write(path, stringBuffer.toString().getBytes());
    }

    private String buildLink(MailProperty mailProperty, String applicationName) {
        String prop = "";
        switch (applicationName) {
            case "FDA":
                prop = "dist-foreign";
                break;
            case "LM":
                prop = "dist-sharefeeder";
                break;
            case "CDP":
                prop = "dist-modeling";
                break;
        }
        return String.format(mailProperty.getReleaseNotesLink(), prop);
    }

}
