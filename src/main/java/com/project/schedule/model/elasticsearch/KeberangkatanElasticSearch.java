package com.project.schedule.model.elasticsearch;

import com.project.schedule.dto.PesawatDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Document(indexName = "keberangkatan")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeberangkatanElasticSearch {

    @Id
    private int id;
    private UUID uuid;
    private String tanggal;
    private String jadwalKeberangkatan;
    private String jadwalKedatangan;
    private String asal;
    private String tujuan;

    @Field(type = FieldType.Object)
    private PesawatDto pesawat;
}
