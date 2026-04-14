package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//This will handle report initialization.
public class ExtentManager {
	private static ExtentReports extent;

    public static ExtentReports getInstance() {
    	 if (extent == null) {
             ExtentSparkReporter reporter = new ExtentSparkReporter("reports/extent-report.html");
             reporter.config().setDocumentTitle("SauceDemo Automation Report");
             reporter.config().setReportName("Regression Suite");
//             reporter.config().setSystemInfo("Host name", "localhost");
//             reporter.setSystemInfo("Environemnt", "QA");
//             reporter.setSystemInfo("user", " nagendra");
             extent = new ExtentReports();
             extent.attachReporter(reporter);
         }
         return extent;

    }

}
