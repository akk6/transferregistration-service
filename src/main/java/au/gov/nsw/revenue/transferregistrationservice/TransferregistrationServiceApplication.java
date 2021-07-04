package au.gov.nsw.revenue.transferregistrationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"au.gov.nsw.revenue.transferregistrationservice"})
public class TransferregistrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransferregistrationServiceApplication.class, args);
	}
}
