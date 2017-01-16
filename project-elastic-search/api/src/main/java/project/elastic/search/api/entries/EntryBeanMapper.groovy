package project.elastic.search.api.entries

import org.springframework.stereotype.Component
import project.elastic.search.elastic.search.elastic.search.domain.entry.Entry

@Component
class EntryBeanMapper {

    EntryBean map(Entry entry) {
        return new EntryBean(
                uniqueId: entry.uniqueId,
                name: entry.name,
                value: entry.value
        )
    }
}
