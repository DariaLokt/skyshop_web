package org.skypro.skyshop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    public void givenEmptyStorage_whenSearch_thenReturnNothing() {
        Mockito.when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(Collections.emptyList(),searchService.search("l"));
    }

    @Test
    public void givenNotSuchObjectInStorage_whenSearch_thenReturnNothing() {
        Searchable storage1 = new Article(UUID.randomUUID(),"B", "B");
        Searchable storage2 = new SimpleProduct(UUID.randomUUID(),"C", 100);
        Mockito.when(storageService.getAllSearchable()).thenReturn(List.of(storage1,storage2));
        Assertions.assertEquals(Collections.emptyList(),searchService.search("A"));
    }

    @Test
    public void givenThereIsTheObject_whenSearch_thenReturnTheObject() {
        Searchable searching = new SimpleProduct(UUID.randomUUID(), "A",5);
        Searchable storage1 = new Article(UUID.randomUUID(),"B", "B");
        Searchable storage2 = new SimpleProduct(UUID.randomUUID(),"C", 100);
        Mockito.when(storageService.getAllSearchable()).thenReturn(List.of(storage1,storage2,searching));
        List<SearchResult> allegedResult = Stream.of(searching)
                .map(SearchResult::fromSearchable)
                .toList();
        Assertions.assertEquals(allegedResult,searchService.search(searching.getSearchTerm()));
    }
}
