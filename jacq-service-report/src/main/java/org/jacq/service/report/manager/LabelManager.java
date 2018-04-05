package org.jacq.service.report.manager;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.jacq.common.model.report.WorkLabel;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.rest.DerivativeService;
import org.jacq.common.rest.report.LabelService;
import org.jacq.common.util.ServicesUtil;
import org.jacq.service.report.ApplicationManager;
import org.jacq.service.report.JacqConfig;

/**
 * Business logic for label printing process
 *
 * @author wkoller
 */
@ManagedBean
public class LabelManager {

    public static String REPORT_PATH = null;

    private static final Logger LOGGER = Logger.getLogger(LabelManager.class.getName());

    @PersistenceContext
    protected EntityManager em;

    @Inject
    protected ApplicationManager applicationManager;

    @Inject
    protected JacqConfig jacqConfig;

    protected DerivativeService derivativeSerive;

    // Context key for birt reporting
    public static final String APP_CONTEXT_KEY_WORKLABELDATASET = "APP_CONTEXT_KEY_WORKLABELDATASET";
    protected IReportEngine reportEngine;

    @PostConstruct
    public void init() {
        this.reportEngine = applicationManager.getReportEngine();

        LabelManager.REPORT_PATH = jacqConfig.getString(JacqConfig.BIRT_WORK_LABEL);
        this.derivativeSerive = ServicesUtil.getDerivativeService();
    }

    /**
     * @see LabelService#getWork(java.lang.String, long)
     */
    public Response getWork(String type, Long derivativeId) throws EngineException {
        List<BotanicalObjectDerivative> results = this.derivativeSerive.find(type, derivativeId, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        // if no result is found, return an error
        if (results == null || results.size() <= 0) {
            throw new NotFoundException("Derivative of type '" + type + "' and derivativeId '" + derivativeId + "' not found!");
        }

        // convert results to WorkLabel POJOs for passing data to BIRT Engine
        List<WorkLabel> workLabels = new ArrayList<>();
        for (BotanicalObjectDerivative botanicalObjectDerivative : results) {
            WorkLabel workLabel = new WorkLabel();
            workLabel.setAccessionNumber(botanicalObjectDerivative.getAccessionNumber());

            // extract label annotation and scientific name
            workLabel.setLabelAnnotation(botanicalObjectDerivative.getLabelAnnotation());
            workLabel.setScientificName(botanicalObjectDerivative.getScientificName());

            // add work-label to output queue
            workLabels.add(workLabel);
        }

        // create report instance
        IReportRunnable report = reportEngine.openReportDesign(REPORT_PATH);

        // setup task for rendering the labels, make sure to set ClassLoader & LivingPlant dataset
        IRunAndRenderTask task = reportEngine.createRunAndRenderTask(report);
        task.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, LabelManager.class.getClassLoader());
        task.getAppContext().put(APP_CONTEXT_KEY_WORKLABELDATASET, workLabels.iterator());

        // output stream for returning the rendered pdf
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // setup pdf rendering options
        PDFRenderOption options = new PDFRenderOption();
        options.setOutputFormat("pdf");
        options.setOutputStream(baos);
        task.setRenderOption(options);

        // finally run the task
        task.run();

        // get exceptions
        for (EngineException error : (List<EngineException>) task.getErrors()) {
            LOGGER.log(Level.SEVERE, error.getMessage(), error);
        }

        // close task
        task.close();

        // return the produces PDF
        return Response.ok(baos.toByteArray()).header("Content-Disposition", "attachment; filename=hbv_worklabel.pdf").build();

    }
}
