package project.elastic.search.api.entries

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import project.elastic.search.elastic.search.elastic.search.domain.entry.Entries
import project.elastic.search.elastic.search.elastic.search.domain.entry.commands.create.CreateEntry
import project.elastic.search.elastic.search.elastic.search.domain.entry.commands.create.CreateEntryHandler
import project.elastic.search.elastic.search.elastic.search.domain.entry.commands.delete.DeleteEntry
import project.elastic.search.elastic.search.elastic.search.domain.entry.commands.delete.DeleteEntryHandler
import spock.lang.Specification
import spock.lang.Unroll

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric as random

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EntriesControllerSpec extends Specification {

    @Autowired
    private TestRestTemplate restTemplate

    @Autowired
    private CreateEntryHandler createEntryHandler

    @Autowired
    private DeleteEntryHandler deleteEntryHandler

    @Autowired
    private Entries entries

    def 'should find entries'() {
        given:
            String uniqueId = createEntryHandler.handle(new CreateEntry(name: 'random name', value: 'random value'))
        when:
            ResponseEntity<String> response = restTemplate.getForEntity('/entries', String)
        then:
            response.statusCode == HttpStatus.OK
        and:
            def body = new JsonSlurper().parseText(response.body)
            def item = body.content.find { it.uniqueId == uniqueId }
            item.name == 'random name'
            item.value == 'random value'
        cleanup:
            deleteEntryHandler.handle(new DeleteEntry(uniqueId: uniqueId))
    }

    @Unroll
    def 'should find entry by query = `#query` that matches entry with name = `#name` and value =`#value`'() {
        given:
            String uniqueId = createEntryHandler.handle(new CreateEntry(name: name, value: value))
        when:
            ResponseEntity<String> response = restTemplate.getForEntity("/entries/search?query=${query}", String)
        then:
            response.statusCode == HttpStatus.OK
        and:
            def body = new JsonSlurper().parseText(response.body)
            def item = body.content.find { it.uniqueId == uniqueId }
            item.name == name
            item.value == value
        cleanup:
            deleteEntryHandler.handle(new DeleteEntry(uniqueId: uniqueId))
        where:
            name                             | value                             | query
            "name ${random(10)}"             | ''                                | 'name'
            "name${random(10)}"              | ''                                | 'name'
            "${random(10)}name"              | ''                                | 'name'
            "first ${random(10)} second"     | ''                                | 'name second'
            "first${random(10)}second"       | ''                                | 'name second'
            "${random(10)}name${random(10)}" | ''                                | 'name'
            ''                               | "value ${random(10)}"             | 'value'
            ''                               | "value${random(10)}"              | 'value'
            ''                               | "${random(10)}value"              | 'value'
            ''                               | "first ${random(10)} second"      | 'first second'
            ''                               | "first${random(10)}second"        | 'first second'
            ''                               | "${random(10)}value${random(10)}" | 'value'
    }
}
