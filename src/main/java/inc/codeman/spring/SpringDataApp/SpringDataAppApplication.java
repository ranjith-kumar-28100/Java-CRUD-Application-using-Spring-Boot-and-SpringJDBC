package inc.codeman.spring.SpringDataApp;

import inc.codeman.spring.SpringDataApp.dao.PersonJdbcDAO;
import inc.codeman.spring.SpringDataApp.dao.PersonJdbcDAO;
import inc.codeman.spring.SpringDataApp.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.Date;

@SpringBootApplication
public class SpringDataAppApplication implements CommandLineRunner {

	@Autowired
	private PersonJdbcDAO personJdbcDAO;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public static void main(String[] args) {
		SpringApplication.run(SpringDataAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All DB Values => {}",personJdbcDAO.findAll());
		logger.info("DB Value of user id 10001 => {}",personJdbcDAO.findById(10001));
		logger.info("DB Value of users with  name James => {}",personJdbcDAO.findByName("James"));
		logger.info("DB Value of users in Amsterdam => {}",personJdbcDAO.findByLocation("Amsterdam"));
		logger.info("DB Value of users with name Johny and in location Amsterdam => {} ",personJdbcDAO.findByNameAndLocation("Johny","Amsterdam"));
		if (personJdbcDAO.deleteById(10001) == 1) {
			logger.info("User with id 10001 got deleted");
		} else {
			logger.info("No user with id 10001");
		}
		if(personJdbcDAO.insert(new Person(10005,"Mohandas","Gujarat",Date.from(Instant.now())))==1)
			logger.info("User with id 10005 got created");
		logger.info("Updated All DB Values => {}",personJdbcDAO.findAll());

		if(personJdbcDAO.update(new Person(10005,"Das","Pune",Date.from(Instant.now())))==1) {
			logger.info("User with id 10005 got updated");
			logger.info("Updated info=> {}",personJdbcDAO.findById(10005));
		}


	}
}
