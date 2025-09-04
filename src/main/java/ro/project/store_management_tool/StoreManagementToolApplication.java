package ro.project.store_management_tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ro.project.store_management_tool"})
public class StoreManagementToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreManagementToolApplication.class, args);
	}

}
