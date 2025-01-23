package me.k1mb.education.utils

import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@SpringJUnitConfig
@Sql(
    "classpath:sql/data.sql",
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS,
)
abstract class IntegrationTestBase {
    companion object {
        @Container
        private val container = PostgreSQLContainer("postgres:17.2")

        @BeforeAll
        @JvmStatic
        fun runContainer() {
            container.start()
        }

        class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
            override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
                TestPropertyValues
                    .of(
                        "spring.datasource.url=" + container::getJdbcUrl,
                        "spring.datasource.username=" + container::getUsername,
                        "spring.datasource.password=" + container::getPassword,
                    ).applyTo(configurableApplicationContext.environment)
            }
        }

        // из-за Sql.ExecutionPhase.BEFORE_TEST_CLASS он грузится до создания контейнера
//                @DynamicPropertySource
        //              @JvmStatic
        //            fun postgresProperties(registry: DynamicPropertyRegistry) {
        //              registry.add("spring.datasource.url", container::getJdbcUrl)
        //            registry.add("spring.datasource.username", container::getUsername)
        //          registry.add("spring.datasource.password", container::getPassword)
        //    }
    }
}
