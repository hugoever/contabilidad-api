package py.edu.ucsa.contabilidad.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = {"py.edu.ucsa.contabilidad.api.web.controllers",
										   "py.edu.ucsa.contabilidad.api.core.services",
										   "py.edu.ucsa.contabilidad.api.dao"})
@EnableAutoConfiguration
@Import(JpaConfiguration.class)
@EnableTransactionManagement
public class ContabilidadApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContabilidadApiApplication.class, args);
	}

}

