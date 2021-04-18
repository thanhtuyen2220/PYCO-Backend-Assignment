package com.Pyco.Assginment.model;

import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ToDo_Task")
@Builder
@QueryEntity
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {
    @Id
    @Getter
    @Setter
    private ObjectId id;

    @Setter
    @Getter
    private String tittle;

    @Getter
    @Setter
    private String Content;

    @Getter
    @Setter
    private String priority;

    @Getter
    @Setter
    private boolean status = false;

    @Getter
    @Setter
    @DBRef
    private User author;
}
