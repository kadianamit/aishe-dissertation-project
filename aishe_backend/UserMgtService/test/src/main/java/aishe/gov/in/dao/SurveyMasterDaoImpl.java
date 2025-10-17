package aishe.gov.in.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class SurveyMasterDaoImpl implements SurveyMasterDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(SurveyMasterDaoImpl.class);

	@Override
	public SurveyMasterEO getLatestClosedSurveyYearDetails() {

		logger.info("ExposedApiDaoImpl : getLatestClosedSurveyYearDetails Method Invoked");
		Session session = sessionFactory.openSession();

		String currentDate = getCurrentDateInYYYYMMDDFormat();

		try {
			Query<SurveyMasterEO> query = session.createQuery(
					"from SurveyMasterEO where endDate < '" + currentDate + "' order by surveyYear desc",
					SurveyMasterEO.class);

			if (query.list().size() > 0) {

				return query.list().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			session.close();
		}

		return null;
	}
	
	private String getCurrentDateInYYYYMMDDFormat() {

		Date currentDate = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		return sdf.format(currentDate);
	}

}
