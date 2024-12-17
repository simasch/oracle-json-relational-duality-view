package ch.martinelli.demo.jdv;

import org.springframework.boot.SpringApplication;

public class TestOracleJsonDualityViewDemoApplication {

	public static void main(String[] args) {
		SpringApplication.from(OracleJsonDualityViewDemoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
