import com.globallogic.psv.hibernate.entity.Activity;
import com.globallogic.psv.hibernate.entity.Report;
import com.globallogic.psv.hibernate.factory.SessionFactoryBuilder;
import com.globallogic.psv.hibernate.service.ActivityService;
import com.globallogic.psv.hibernate.service.BuildingService;
import com.globallogic.psv.hibernate.service.ReportService;
import org.apache.log4j.Logger;

import java.util.List;

public class HibernateProjectDemo {

    private static Logger logger = Logger.getLogger(HibernateProjectDemo.class);

    public static void main(String[] args) {

        //a. Get all Reports information for particular user
        ReportService reportService = new ReportService();
        Long userId = 1L;
        List<Report> reports = reportService.getReportsByParticularUser(userId);

        //b. Get All activities information for Particular user and Specified building
        ActivityService activityService = new ActivityService();
        Long user2Id = 2L;
        Long buildingId = 5L;
        List<Activity> activities = activityService.getActivitiesForUserAndSpecBuilding(user2Id, buildingId);

        //c. Set Buildings in non-Active state where total price of all activities is more than specified value
        Long building2Id = 7L;
        double specifiedValue = 4000.0;
        BuildingService buildingService = new BuildingService();
        logger.info("Total price = " + activityService.getTotalActivityPriceByBuildingId(building2Id));
        buildingService.updateIsActiveStatus(building2Id, specifiedValue);
        logger.info("Active status was updated");

        //d1. Get total Activities price for particular report
        Long reportId = 8L;
        double totalActivityPriceByReportId = activityService.getTotalActivityPriceByReportId(reportId);
        logger.info("Total price of activities for report id=" + reportId + " = " + totalActivityPriceByReportId);

        //d2. Get total Activities price for particular building
        Long building3Id = 4L;
        double totalActivityPriceByBuildingId = activityService.getTotalActivityPriceByBuildingId(building3Id);
        logger.info("Total price of activities for specific building id=" + building3Id +
                " = " + totalActivityPriceByBuildingId);

        //d3. Get total Activities price for particular user
        Long user3Id = 3L;
        double totalActivityPriceByUserId = activityService.getTotalActivityPriceByUserId(user3Id);
        logger.info("Total price of activities for specific user id=" + user3Id + " = " + totalActivityPriceByUserId);

    }
}
