package kaur.srini.srinitrial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SrinitrialApplication {

	static Logger logger = LoggerFactory.getLogger(SrinitrialApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SrinitrialApplication.class, args);
	}

}
