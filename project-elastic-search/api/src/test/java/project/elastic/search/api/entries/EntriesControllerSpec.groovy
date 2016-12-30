package project.elastic.search.api.entries

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import project.elastic.search.elastic.search.elastic.search.domain.entry.commands.CreateEntry
import project.elastic.search.elastic.search.elastic.search.domain.entry.commands.CreateEntryHandler
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EntriesControllerSpec extends Specification {

    @Autowired
    private TestRestTemplate restTemplate

    @Autowired
    private CreateEntryHandler handler

    def 'should find commands'() {
        given:
            handler.handle(new CreateEntry(name: 'random name', value: 'random value'))
        when:
            ResponseEntity<String> response = restTemplate.getForEntity('/entries', String)
        then:
            response.statusCode == HttpStatus.OK
            def body = new JsonSlurper().parseText(response.body)
            body != null
    }
}
