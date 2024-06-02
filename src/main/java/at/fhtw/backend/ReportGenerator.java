package at.fhtw.backend;

import at.fhtw.backend.model.Log;
import at.fhtw.backend.model.Tour;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ReportGenerator {
    private final Tour tour;

    private int difficulty;
    private int rating;
    public ReportGenerator(Tour tour) {
        this.tour = tour;
        int logLength = tour.getLog().size();
        for(Log log : tour.getLog()){
            difficulty += log.getDifficulty();
            rating += log.getRating();
        }
        difficulty /= logLength;
        rating /= logLength;
    }
    public String parseThymeleafTemplate() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);


        Context context = new Context();
        context.setVariable("title", tour.getTourName());
        context.setVariable("description", tour.getDescription());
        context.setVariable("start", tour.getStartPoint());
        context.setVariable("end", tour.getEndPoint());
        context.setVariable("distance", tour.getDistance());
        context.setVariable("time", tour.getTime());
        context.setVariable("difficulty", difficulty);
        context.setVariable("rating", rating);
        context.setVariable("log", tour.getLog());

        return templateEngine.process("thymeleaf_template", context);
    }
    public void generatePdfFromHtml(String html) throws IOException {
        String outputFolder = System.getProperty("user.dir") + File.separator + "thymeleaf.pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }

}
