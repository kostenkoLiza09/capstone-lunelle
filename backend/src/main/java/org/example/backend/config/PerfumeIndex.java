package org.example.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "perfumeindex")
public class PerfumeIndex {


    @Id
    private String id;

    @Field(name = "perfumeInfo", type = FieldType.Text)
    private String perfumeInfo;
}
