package start.domain;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    @SequenceGenerator(sequenceName = "user_Seq", name = "userSeq", initialValue = 1000, allocationSize = 1)
    private int Id;

    @Column (name="name")
    private String name;

    @Column (name="cost")
    private int cost;

    @Column (name="description")
    private String description;




}