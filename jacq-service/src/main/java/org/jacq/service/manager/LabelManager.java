package org.jacq.service.manager;

import org.jacq.service.ApplicationManager;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.jacq.common.model.jpa.TblLivingPlant;
import org.jacq.service.JacqConfig;

/**
 * Business logic for label printing process
 *
 * @author wkoller
 */
@ManagedBean
public class LabelManager {

    /**
     * TODO: load report design configuration from database
     */
    public static String REPORT_PATH = null;

    private static final Logger LOGGER = Logger.getLogger(LabelManager.class.getName());

    @PersistenceContext
    protected EntityManager em;

    @Inject
    protected ApplicationManager applicationManager;

    @Inject
    protected JacqConfig jacqConfig;

    // Context key for birt reporting
    public static final String APP_CONTEXT_KEY_LIVINGPLANTDATASET = "APP_CONTEXT_KEY_LIVINGPLANTDATASET";

    @PostConstruct
    public void init() {
        LabelManager.REPORT_PATH = jacqConfig.getString(JacqConfig.BIRT_WORK_LABEL);
    }

    /**
     * @see LabelService#getWork()
     */
    public Response getWork(long botanicalObjectId) throws Exception {
        // find the corresponding living plant entry for the passed botanical object
        TypedQuery<TblLivingPlant> query = em.createNamedQuery("TblLivingPlant.findById", TblLivingPlant.class);
        query.setParameter("id", botanicalObjectId);
        List<TblLivingPlant> results = query.getResultList();

        // if no result is found, return an error
        if (results == null || results.size() <= 0) {
            throw new NotFoundException("Living Plant with id '" + botanicalObjectId + "' not found!");
        }

        // get a reference to the report engine
        IReportEngine reportEngine = applicationManager.getReportEngine();
        // create report instance
        IReportRunnable report = reportEngine.openReportDesign(REPORT_PATH);

        // setup task for rendering the labels, make sure to set ClassLoader & LivingPlant dataset
        IRunAndRenderTask task = reportEngine.createRunAndRenderTask(report);
        task.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, LabelManager.class.getClassLoader());
        task.getAppContext().put(APP_CONTEXT_KEY_LIVINGPLANTDATASET, results.iterator());

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
