package project.elastic.search.elastic.search.elastic.search.domain.entry;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EntryRepository extends ElasticsearchRepository<Entry, String> {
    List<Entry> findByName(String name);
}
